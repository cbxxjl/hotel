package com.cbxjl.hotel.manager.service.Impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.constant.CacheConstants;
import com.cbxjl.hotel.common.domain.LoginUser;
import com.cbxjl.hotel.common.exception.BusinessException;
import com.cbxjl.hotel.common.utils.redis.RedisUtils;
import com.cbxjl.hotel.manager.core.dos.UserDO;
import com.cbxjl.hotel.manager.core.dto.*;
import com.cbxjl.hotel.manager.core.entity.User;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.manager.repository.UserRepository;
import com.cbxjl.hotel.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 *
 * @author : cbxjl
 * @date : 2024/2/28 14:08
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Value("${default.maximumInput}")
    private int MAXIMUMINPUT;

    /**
     * 用户表分页查询
     *
     * @param pageParam 分页参数
     * @return 分页结果
     */
    @Override
    public PageResult<UserPageDTO> listPage(UserPageParam pageParam) {
        Page<User> page = userRepository.page(pageParam);
        List<User> records = page.getRecords();
        List<UserDO> userDOList = records.stream().map(User::poToDo).collect(Collectors.toList());

        List<UserPageDTO> userPageDTOList = userDOList.stream().map(UserDO::doToPage).collect(Collectors.toList());
        return new PageResult<>(userPageDTOList, page.getTotal(), page.getSize(), page.getCurrent());
    }

    /**
     * 增加用户
     *
     * @param userAddDTO 用户增加DTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserAddDTO userAddDTO) {
        UserDO userDO = userAddDTO.addToDo();
        userRepository.addUser(userDO);
    }

    /**
     * 根据id回显用户信息
     *
     * @param id 用户id
     * @return 用户editDTO
     */
    @Override
    public UserEditDTO getById(Long id) {
        UserDO userDO = userRepository.getUserById(id);
        return userDO.doToEdit();
    }

    /**
     * 更新用户信息
     *
     * @param userEditDTO 用户编辑DTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserEditDTO userEditDTO) {
        UserDO userDO = userEditDTO.editToDo();
        userRepository.update(userDO);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
       userRepository.delete(id);
    }

    /**
     * 重置密码
     *
     * @param id 用户id
     */
    @Override
    public void resetPwd(Long id) {
        UserDO userDO = userRepository.getUserById(id);
        userDO.setPassword(BCrypt.hashpw("123456"));
        userRepository.update(userDO);
    }

    /**
     * 修改密码
     *
     * @param userPwdDTO 用户密码DTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePwd(UserPwdDTO userPwdDTO) {
        Long id = userPwdDTO.getId();
        UserDO userDO = userRepository.getUserById(id);
        //登陆失败，键
        String errorKey = CacheConstants.PWD_ERR_CNT_KEY + userDO.getAccount();

        //获取用户修改密码错误次数，默认为0
        int errorNumber = ObjectUtil.defaultIfNull(RedisUtils.getCacheObject(errorKey), 0);
        if (errorNumber >= MAXIMUMINPUT) {
            //冻结用户
            userDO.setStatus(0);
            userRepository.update(userDO);
            RedisUtils.deleteObject(errorKey);
            throw new BusinessException("密码输入错误" + MAXIMUMINPUT + "次，账号冻结，请联系管理员");
        }
        //密码不正确
        if (!BCrypt.checkpw(userPwdDTO.getPassword(), userDO.getPassword())) {
            //错误次数递增
            errorNumber++;
            RedisUtils.setCacheObject(errorKey, errorNumber, Duration.ofMinutes(1));
            log.error("密码错误，还剩" + (MAXIMUMINPUT - errorNumber) + "次");
            throw new BusinessException("密码错误，还剩" + (MAXIMUMINPUT - errorNumber) + "次");
        }

        //密码正确，将修改密码失败的标识符清空
        RedisUtils.deleteObject(errorKey);
        userDO.setPassword(BCrypt.hashpw(userPwdDTO.getNewPassword()));
        userRepository.update(userDO);
    }


}
