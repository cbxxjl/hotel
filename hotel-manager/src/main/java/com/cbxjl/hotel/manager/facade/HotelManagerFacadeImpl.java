package com.cbxjl.hotel.manager.facade;

import com.cbxjl.hotel.manager.core.dos.UserDO;
import com.cbxjl.hotel.manager.repository.UserRepository;
import dos.LoginUserDO;
import facade.hotelManager.HotelManagerFacade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 酒店管理外部接口实现类
 *
 * @author : cbxjl
 * @date : 2024/2/28 15:21
 */
@Service
public class HotelManagerFacadeImpl implements HotelManagerFacade {
    @Resource
    private UserRepository userRepository;

    /**
     * 根据账号获取用户
     *
     * @param account 账号
     * @return 用户
     */
    @Override
    public LoginUserDO getUserByAccount(String account) {
        UserDO userDO = userRepository.getUserByAccount(account);
        return userDO.doToLogin();
    }

    /**
     * 更新用户信息
     *
     * @param loginUserDO 用户信息
     */
    @Override
    public void updateById(LoginUserDO loginUserDO) {
        userRepository.updateByLoginUser(loginUserDO);
    }

    /**
     * 根据id获取用户
     *
     * @param userId 用户id
     * @return LoginUserDO
     */
    @Override
    public LoginUserDO getUserById(Long userId) {
        UserDO userDO = userRepository.getUserById(userId);
        return userDO.doToLogin();
    }
}
