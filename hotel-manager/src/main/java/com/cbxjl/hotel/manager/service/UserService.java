package com.cbxjl.hotel.manager.service;

import com.cbxjl.hotel.common.domain.KeyValueDTO;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.manager.core.dto.*;

import java.util.List;

/**
 * 用户服务
 *
 * @author : cbxjl
 * @date : 2024/2/28 14:07
 */
public interface UserService {

    /**
     * 用户表分页查询
     *
     * @param pageParam 分页参数
     * @return 分页结果
     */
    PageResult<UserPageDTO> listPage(UserPageParam pageParam);

    /**
     * 增加用户
     *
     * @param userAddDTO 用户增加DTO
     */
    void addUser(UserAddDTO userAddDTO);

    /**
     * 根据id回显用户信息
     *
     * @param id 用户id
     * @return 用户editDTO
     */
    UserEditDTO getById(Long id);

    /**
     * 更新用户信息
     *
     * @param userEditDTO 用户编辑DTO
     */
    void update(UserEditDTO userEditDTO);

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    void delete(Long id);

    /**
     * 重置密码
     *
     * @param id 用户id
     */
    void resetPwd(Long id);

    /**
     * 修改密码
     *
     * @param userPwdDTO 用户密码DTO
     */
    void changePwd(UserPwdDTO userPwdDTO);

    /**
     * 获取后勤员工列表
     *
     * @return 后勤员工列表
     */
    List<KeyValueDTO> getLogisticsList();
}
