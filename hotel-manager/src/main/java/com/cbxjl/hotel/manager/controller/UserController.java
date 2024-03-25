package com.cbxjl.hotel.manager.controller;

import com.cbxjl.hotel.common.annotations.CheckUserType;
import com.cbxjl.hotel.common.constant.UserTypeConstants;
import com.cbxjl.hotel.common.domain.KeyValueDTO;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.common.domain.R;
import com.cbxjl.hotel.common.enums.UserType;
import com.cbxjl.hotel.common.utils.StringUtils;
import com.cbxjl.hotel.manager.core.dto.*;
import com.cbxjl.hotel.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 酒店员工（用户）相关接口
 *
 * @author : cbxjl
 * @date : 2024/2/28 13:49
 */
@RestController
@RequestMapping("/hotel/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户分页查询
     *
     * @param pageParam 分页参数
     * @return 分页结果
     */
    @PostMapping("/userList")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR})
    public R<PageResult<UserPageDTO>> userListPage(@RequestBody UserPageParam pageParam) {
        PageResult<UserPageDTO> pageResult = userService.listPage(pageParam);
        return R.ok(pageResult);
    }

    /**
     * 添加用户
     *
     * @param userAddDTO 用户添加DTO
     * @return 添加结果
     */
    @PostMapping("/add")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR})
    public R<Void> addUser(@Valid @RequestBody UserAddDTO userAddDTO) {
        userService.addUser(userAddDTO);
        return R.ok("添加成功");
    }

    /**
     * 编辑回显用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/getById/{id}")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR})
    public R<UserEditDTO> getUserById(@PathVariable Long id) {
        UserEditDTO userEditDTO = userService.getById(id);
        return R.ok(userEditDTO);
    }

    /**
     * 更新用户
     *
     * @param userEditDTO 用户编辑DTO
     * @return 更新结果
     */
    @PostMapping("/update")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR})
    public R<Void> updateUser(@Valid @RequestBody UserEditDTO userEditDTO) {
        userService.update(userEditDTO);
        return R.ok("修改成功");
    }

    /**
     * 获取职位列表
     *
     * @return 职位列表
     */
    @GetMapping("/typeList")
    public R<List<KeyValueDTO>> typeList() {
        List<KeyValueDTO> keyValueDTOList = new ArrayList<>();
        for (UserType userType : UserType.values()) {
            KeyValueDTO keyValueDTO = new KeyValueDTO();
            keyValueDTO.setKey(userType.getUserType());
            keyValueDTO.setValue(userType.getNum());
            keyValueDTOList.add(keyValueDTO);
        }
        return R.ok(keyValueDTOList);
    }

    /**
     * 删除用户
     *
     * @return 删除结果
     */
    @GetMapping("/delete/{id}")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR})
    public R<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return R.ok("删除成功");
    }

    /**
     * 重置密码
     *
     * @return 重置结果
     */
    @GetMapping("/resetPwd/{id}")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR})
    public R<Void> resetPassword(@PathVariable Long id) {
        userService.resetPwd(id);
        return R.ok("密码重置成功");
    }

    /**
     * 修改密码
     *
     * @param userPwdDTO 用户密码DTO
     * @return 修改结果
     */
    @PostMapping("/changePwd")
    public R<Void> changePassword(@RequestBody UserPwdDTO userPwdDTO) {
        userService.changePwd(userPwdDTO);
        return R.ok("修改密码成功");
    }

    /**
     * 获取后勤员工列表 （下拉框）
     *
     * @return 后勤员工列表
     */
    @GetMapping("/getLogisticsList")
    public R<List<KeyValueDTO>> getLogisticsList() {
        List<KeyValueDTO> keyValueDTOList = userService.getLogisticsList();
        return R.ok(keyValueDTOList);
    }
}
