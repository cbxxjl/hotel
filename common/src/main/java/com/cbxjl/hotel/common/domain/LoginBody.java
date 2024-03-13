package com.cbxjl.hotel.common.domain;

import com.cbxjl.hotel.common.constant.UserConstants;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 统一登录对象
 *
 * @author : cbxjl
 * @date : 2024/1/29 11:54
 */
@Data
public class LoginBody {
    /**
     * 账号
     */
    @NotBlank(message = "{user.username.not.blank}")
    @Length(min = UserConstants.USERNAME_MIN_LENGTH, max = UserConstants.USERNAME_MAX_LENGTH, message = "{user.username.length.valid}")
    private String account;

    /**
     * 用户密码
     */
    @NotBlank(message = "{user.password.not.blank}")
    @Length(min = UserConstants.PASSWORD_MIN_LENGTH, max = UserConstants.PASSWORD_MAX_LENGTH, message = "{user.password.length.valid}")
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码唯一标识
     */
    private String uuid;
}
