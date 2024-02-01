package com.cbxjl.hotel.common.core.respository.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.cbxjl.hotel.common.core.domain.dos.UserDO;
import com.cbxjl.hotel.common.core.domain.entity.User;
import com.cbxjl.hotel.common.core.mapper.UserMapper;
import com.cbxjl.hotel.common.core.respository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 用户仓储实现类
 *
 * @author : cbxjl
 * @date : 2024/2/1 11:26
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Resource
    private UserMapper userMapper;
    /**
     * 根据用户id获取用户
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public UserDO getUserById(Long userId) {
        User user = userMapper.selectById(userId);
        return user.poToDo();
    }

    /**
     * 根据用户名获取用户
     *
     * @param userName 用户名
     * @return
     */
    @Override
    public UserDO getUserByUserName(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotEmpty(userName), User::getUserName, userName);

        User user = userMapper.selectOne(queryWrapper);
        return user.poToDo();
    }

    /**
     * 更新用户
     *
     * @param userDO
     */
    @Override
    public void updateById(UserDO userDO) {
        User user = userDO.doToPo();
        userMapper.updateById(user);
    }
}
