package com.cbxjl.hotel.manager.core.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cbxjl.hotel.manager.core.dos.UserDO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户增加DTO
 *
 * @author : cbxjl
 * @date : 2024/3/6 15:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDTO {
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

    /**
     * 职位（1：管理员，2：前台，3：财务，4：后勤）
     */
    @NotNull(message = "职位不能为空")
    private Integer userType;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    private String phone;

    /**
     * 头像地址
     */
    private String avatar;

    public UserDO addToDo() {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(this, userDO);

        return userDO;
    }
}
