package com.cbxjl.hotel.manager.core.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户分页查询
 *
 * @author : cbxjl
 * @date : 2024/2/28 13:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPageDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 账号
     */
    private String account;


    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户类型（1：管理员，2：前台，3：财务，4：后勤）
     */
    private Integer userType;
    private String userTypeStr;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 性别:0-男，1-女
     */
    private Integer sex;
    private String sexStr;
}
