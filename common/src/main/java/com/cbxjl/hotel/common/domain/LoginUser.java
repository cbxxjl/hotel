package com.cbxjl.hotel.common.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 当前登录用户
 *
 * @author : cbxjl
 * @date : 2024/3/7 15:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser{

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户类型（1：管理员，2：员工，3：普通用户）
     */
    private Integer userType;
    private String userTypeStr;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;
}
