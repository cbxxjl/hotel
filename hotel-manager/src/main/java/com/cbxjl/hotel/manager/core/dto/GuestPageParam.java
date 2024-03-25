package com.cbxjl.hotel.manager.core.dto;

import com.cbxjl.hotel.common.domain.PageParams;
import lombok.Data;

/**
 * 客户分页查询参数
 *
 * @author : cbxjl
 * @date : 2024/3/23 14:26
 */
@Data
public class GuestPageParam extends PageParams {
    /**
     * 客户名字
     */
    private String name;

    /**
     * 客户等级
     */
    public Integer level;

    /**
     * 客户性别
     */
    public Integer sex;
}
