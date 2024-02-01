package com.cbxjl.hotel.system.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cbxjl.hotel.common.constant.CacheConstants;
import com.cbxjl.hotel.common.core.domain.dos.UserDO;
import com.cbxjl.hotel.common.core.domain.dto.UserInfoDTO;
import com.cbxjl.hotel.common.core.domain.entity.User;
import com.cbxjl.hotel.common.core.mapper.UserMapper;
import com.cbxjl.hotel.common.core.respository.UserRepository;
import com.cbxjl.hotel.common.exception.BusinessException;
import com.cbxjl.hotel.common.utils.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.function.Supplier;

/**
 * 登录服务
 *
 * @author : cbxjl
 * @date : 2024/1/29 14:32
 */
@Service
@Slf4j
public class LoginService {
    @Resource
    private UserRepository userRepository;
    private static final int MAXIMUMINPUT = 5;

    /**
     * 密码登录
     *
     * @return token
     */
    public String passwordLogin(String username, String password, String code, String uuid) {
        //判断验证码
        validateCaptcha(code, uuid);
        UserDO userDO = getUserByUserName(username);
        //此处的密码采用函数式接口将明文和加密后的密文进行比较。并返回比较结果
        checkLogin(username, () -> !BCrypt.checkpw(password, userDO.getPassword()), userDO);
        //登录
        StpUtil.login(userDO.getId());
        return StpUtil.getLoginIdAsString();
    }

    /**
     * 登录校验
     *
     * @param username 用户名
     * @param supplier 密码判断
     * @param userDO     用户
     */
    private void checkLogin(String username, Supplier<Boolean> supplier, UserDO userDO) {
        //登陆失败，键
        String errorKey = CacheConstants.PWD_ERR_CNT_KEY + username;

        // 获取用户登录错误次数，默认为0
        int errorNumber = ObjectUtil.defaultIfNull(RedisUtils.getCacheObject(errorKey), 0);
        if (errorNumber >= MAXIMUMINPUT) {
            //冻结用户
            userDO.setStatus(0);
            userRepository.updateById(userDO);
            RedisUtils.deleteObject(errorKey);
            throw new BusinessException("密码输入错误" + MAXIMUMINPUT + "次，账号冻结，请联系管理员");
        }

        //密码不正确
        if (supplier.get()) {
            //错误次数递增
            errorNumber++;
            RedisUtils.setCacheObject(errorKey, errorNumber, Duration.ofMinutes(1));
            log.error("密码错误，还剩" + (MAXIMUMINPUT - errorNumber) + "次");
            throw new BusinessException("密码错误，还剩" + (MAXIMUMINPUT - errorNumber) + "次");
        }

        //密码正确，将登陆失败的标识符清空
        RedisUtils.deleteObject(errorKey);
    }

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    private UserDO getUserByUserName(String username) {
        UserDO userDO = userRepository.getUserByUserName(username);
        if (ObjectUtil.isEmpty(userDO)) {
            log.error("用户不存在：{}", username);
            throw new BusinessException("用户不存在:" + username);
        } else if (userDO.getStatus() == 0) {
            log.error("用户被冻结: {}", username);
            throw new BusinessException("用户被冻结:" + username);
        }
        return userDO;
    }

    /**
     * 校验验证码
     *
     * @param code 验证码
     * @param uuid 唯一标识
     */
    public void validateCaptcha(String code, String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.defaultString(uuid, "");
        String captcha = RedisUtils.getCacheObject(verifyKey);
        //验证码只会进行一次判断
        RedisUtils.deleteObject(verifyKey);
        if (captcha == null) {
            throw new BusinessException("验证码为空");
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new BusinessException("验证码不正确");
        }
    }

    /**
     * 获取登录用户
     *
     * @param userId 用户id
     * @return 用户信息dto
     */
    public UserInfoDTO getLoginUser(Long userId) {
        UserDO userDO = userRepository.getUserById(userId);
        return userDO.doToInfo();
    }
}
