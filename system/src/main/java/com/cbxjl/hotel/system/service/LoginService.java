package com.cbxjl.hotel.system.service;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cbxjl.hotel.common.constant.CacheConstants;
import com.cbxjl.hotel.common.domain.LoginUser;
import com.cbxjl.hotel.common.enums.UserType;
import com.cbxjl.hotel.system.domain.dto.LoginUserInfoDTO;
import com.cbxjl.hotel.common.exception.BusinessException;
import com.cbxjl.hotel.common.utils.redis.RedisUtils;
import dos.LoginUserDO;
import facade.hotelManager.HotelManagerFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
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
    public static final String LOGIN_USER_KEY = "loginUser";
    public static final String USER_KEY = "userId";
    @Resource
    private HotelManagerFacade hotelManagerFacade;

    @Value("${default.maximumInput}")
    private int MAXIMUMINPUT;



    /**
     * 密码登录
     *
     * @return token
     */
    public String passwordLogin(String account, String password, String code, String uuid) {
        //判断验证码
        validateCaptcha(code, uuid);
        LoginUserDO loginUserDO = getUserByAccount(account);
        //此处的密码采用函数式接口将明文和加密后的密文进行比较。并返回比较结果
        checkLogin(account, () -> !BCrypt.checkpw(password, loginUserDO.getPassword()), loginUserDO);
        //登录
        StpUtil.login(loginUserDO.getId());

        //将通过id获取loginUser并存放到SaSession中，方便后续读取
        Long userId = StpUtil.getLoginIdAsLong();
        LoginUser loginUser = getLoginUser(userId);
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);

        return StpUtil.getLoginIdAsString();
    }

    /**
     * 登录校验
     *
     * @param account     账号
     * @param supplier    密码判断
     * @param loginUserDO 当前登录用户
     */
    private void checkLogin(String account, Supplier<Boolean> supplier, LoginUserDO loginUserDO) {
        //登陆失败，键
        String errorKey = CacheConstants.PWD_ERR_CNT_KEY + account;

        // 获取用户登录错误次数，默认为0
        int errorNumber = ObjectUtil.defaultIfNull(RedisUtils.getCacheObject(errorKey), 0);
        if (errorNumber >= MAXIMUMINPUT) {
            //冻结用户
            loginUserDO.setStatus(0);
            hotelManagerFacade.updateById(loginUserDO);
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
     * 根据账号获取用户
     *
     * @param account 用户名
     * @return 用户
     */
    private LoginUserDO getUserByAccount(String account) {
        LoginUserDO loginUserDO = hotelManagerFacade.getUserByAccount(account);
        if (ObjectUtil.isEmpty(loginUserDO)) {
            log.error("用户不存在：{}", account);
            throw new BusinessException("用户不存在:" + account);
        } else if (loginUserDO.getStatus() == 0) {
            log.error("用户被冻结: {}", account);
            throw new BusinessException("用户被冻结:" + account);
        }
        return loginUserDO;
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
    public LoginUser getLoginUser(Long userId) {
        LoginUserDO loginUserDO = hotelManagerFacade.getUserById(userId);
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(loginUserDO, loginUser);
        loginUser.setUserTypeStr(UserType.getUserType(loginUser.getUserType()));

        return loginUser;
    }
}
