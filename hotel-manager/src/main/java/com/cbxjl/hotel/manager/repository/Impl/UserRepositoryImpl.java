package com.cbxjl.hotel.manager.repository.Impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.domain.KeyValueDTO;
import com.cbxjl.hotel.common.domain.LoginUser;
import com.cbxjl.hotel.common.exception.BusinessException;
import com.cbxjl.hotel.common.utils.SnowIdUtils;
import com.cbxjl.hotel.manager.core.dos.UserDO;
import com.cbxjl.hotel.manager.core.entity.User;
import com.cbxjl.hotel.manager.mapper.UserMapper;
import com.cbxjl.hotel.common.utils.StringUtils;
import com.cbxjl.hotel.manager.core.dto.UserPageParam;
import com.cbxjl.hotel.manager.repository.UserRepository;
import dos.LoginUserDO;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 用户仓储实现类
 *
 * @author : cbxjl
 * @date : 2024/2/28 14:13
 */
@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    @Resource
    private UserMapper userMapper;

    @Value("${default.password}")
    private String defaultPwd;

    /**
     * 用户表分页查询
     *
     * @param pageParam 分页参数
     * @return 分页结果
     */
    @Override
    public Page<User> page(UserPageParam pageParam) {
        Page<User> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(pageParam.getAccount()), User::getAccount, pageParam.getAccount());
        queryWrapper.like(StringUtils.isNotEmpty(pageParam.getUserName()), User::getUserName, pageParam.getUserName());
        queryWrapper.eq(pageParam.getSex() != null, User::getSex, pageParam.getSex());
        queryWrapper.eq(User::getDelFlag, 0);
        queryWrapper.orderByDesc(User::getCreateTime);

        return userMapper.selectPage(page, queryWrapper);
    }

    /**
     * 根据账号获取用户DO
     *
     * @param account 账号
     * @return 用户DO
     */
    @Override
    public UserDO getUserByAccount(String account) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(account), User::getAccount, account);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            log.error("当前账号不存在，{}", account);
            throw new BusinessException("当前账号不存在" + account);
        }
        return user.poToDo();
    }

    /**
     * 检查账号是否重复
     *
     * @param account 账号
     * @return 检查结果
     */
    @Override
    public void checkUserByAccount(String account) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(account), User::getAccount, account);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            log.error(user.getAccount() + "，账号重复了");
            throw new BusinessException(user.getAccount() + "，账号重复了");
        }
    }

    /**
     * 更新用户信息
     *
     * @param loginUserDO 用户信息
     */
    @Override
    public void updateByLoginUser(LoginUserDO loginUserDO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(loginUserDO, userDO);

        userMapper.updateById(userDO.doToPo());
    }

    /**
     * 根据用户id获取用户
     *
     * @param userId 用户id
     * @return 用户
     */
    @Override
    public UserDO getUserById(Long userId) {
        User user = userMapper.selectById(userId);
        if (ObjectUtil.isEmpty(user)) {
            log.error("当前id不存在，{}", userId);
            throw new BusinessException("当前用户不存在");
        }
        return user.poToDo();
    }

    /**
     * 增加用户
     *
     * @param userDO 用户DO
     */
    @Override
    public void addUser(UserDO userDO) {
        User user = userDO.doToPo();
        user.setId(SnowIdUtils.uniqueLong());
        //状态默认正常
        user.setStatus(1);
        user.setPassword(BCrypt.hashpw(defaultPwd));
        userMapper.insert(user);
    }

    /**
     * 更新用户
     *
     * @param userDO 用户do
     */
    @Override
    public void update(UserDO userDO) {
        User user = userDO.doToPo();
        userMapper.updateById(user);
    }

    /**
     * 删除用户 逻辑删除
     *
     * @param id 用户id
     */
    @Override
    public void delete(Long id) {
        User user = userMapper.selectById(id);
        if (ObjectUtil.isEmpty(user)) {
            log.error("当前id不存在，{}", id);
            throw new BusinessException("当前用户不存在");
        }
        user.setDelFlag(1);
        userMapper.updateById(user);
    }

    /**
     * 获取后勤员工列表
     *
     * @return 后勤员工列表
     */
    @Override
    public List<KeyValueDTO> getLogisticsList() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //后勤
        queryWrapper.eq(User::getUserType, 4);
        List<User> users = userMapper.selectList(queryWrapper);

        return users.stream().map(item ->
                new KeyValueDTO(item.getId(), item.getUserName())
        ).collect(Collectors.toList());
    }
}
