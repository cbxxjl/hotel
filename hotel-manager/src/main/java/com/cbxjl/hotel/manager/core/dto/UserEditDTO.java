package com.cbxjl.hotel.manager.core.dto;

import com.cbxjl.hotel.manager.core.dos.UserDO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户编辑DTO
 *
 * @author : cbxjl
 * @date : 2024/3/7 14:36
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    private String account;

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名称不能为空")
    private String userName;

    /**
     * 性别:0-男，1-女
     */
    @NotNull(message = "性别不能为空")
    private Integer sex;
    private String sexStr;

    /**
     * 职位（1：管理员，2：前台，3：财务，4：后勤）
     */
    @NotNull(message = "职位不能为空")
    private Integer userType;
    private String userTypeStr;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    private String phone;

    /**
     * 头像地址
     */
    private String avatar;

    public UserDO editToDo() {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(this, userDO);

        return userDO;
    }
}
