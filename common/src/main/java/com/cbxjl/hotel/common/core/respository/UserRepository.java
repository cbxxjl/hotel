package com.cbxjl.hotel.common.core.respository;

import com.cbxjl.hotel.common.core.domain.dos.UserDO;
import com.cbxjl.hotel.common.core.domain.entity.User;


/**
 * 用户仓储
 *
 * @author : cbxjl
 * @date : 2024/2/1 11:16
 */
public interface UserRepository {
    /**
     * 根据用户id获取用户
     *
     * @param userId 用户id
     * @return
     */
    UserDO getUserById(Long userId);

    /**
     * 根据用户名获取用户
     *
     * @param userName 用户名
     * @return
     */
    UserDO getUserByUserName(String userName);

    /**
     * 更新用户
     *
     * @param userDO
     */
    void updateById(UserDO userDO);
}
