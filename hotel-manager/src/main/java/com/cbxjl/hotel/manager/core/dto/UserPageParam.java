package com.cbxjl.hotel.manager.core.dto;

import com.cbxjl.hotel.common.domain.PageParams;
import lombok.Data;

/**
 * 用户表分页参数
 *
 * @author : cbxjl
 * @date : 2024/2/28 14:15
 */
@Data
public class UserPageParam extends PageParams {
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 性别:0-男，1-女
     */
    private Integer sex;

    /**
     * 账号
     */
    private String account;
}
