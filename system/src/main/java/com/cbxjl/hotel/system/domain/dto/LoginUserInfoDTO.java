package com.cbxjl.hotel.system.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户信息dto
 *
 * @author : cbxjl
 * @date : 2024/2/1 10:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserInfoDTO {
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
}
