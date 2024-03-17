package com.cbxjl.hotel.manager.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.manager.core.dos.UserDO;
import com.cbxjl.hotel.manager.core.dto.UserAddDTO;
import com.cbxjl.hotel.manager.core.entity.User;
import com.cbxjl.hotel.manager.core.dto.UserPageParam;
import dos.LoginUserDO;

/**
 * 用户仓储
 *
 * @author : cbxjl
 * @date : 2024/2/28 14:11
 */
public interface UserRepository {
    /**
     * 用户表分页查询
     *
     * @param pageParam 分页参数
     * @return 分页结果
     */
    Page<User> page(UserPageParam pageParam);

    /**
     * 根据账号获取用户DO
     *
     * @param account 账号
     * @return 用户DO
     */
    UserDO getUserByAccount(String account);

    /**
     * 检查账号是否重复
     *
     * @param account 账号
     * @return 检查结果
     */
    void checkUserByAccount(String account);

    /**
     * 更新用户信息
     *
     * @param loginUserDO 用户信息
     */
    void updateByLoginUser(LoginUserDO loginUserDO);

    /**
     * 根据用户id获取用户
     *
     * @param userId 用户id
     * @return 用户
     */
    UserDO getUserById(Long userId);

    /**
     * 增加用户
     *
     * @param userDO 用户DO
     */
    void addUser(UserDO userDO);

    /**
     * 更新用户
     *
     * @param userDO 用户do
     */
    void update(UserDO userDO);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void delete(Long id);
}
