package com.cbxjl.hotel.manager.core.dto;

import com.cbxjl.hotel.common.domain.PageParams;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 客房分页查询参数
 *
 * @author : cbxjl
 * @date : 2024/3/12 11:51
 */
@Data
public class RoomPageParam extends PageParams {
    /**
     * 房间编号
     */
    private String number;

    /**
     * 房间类型
     */
    private Integer type;

    /**
     * 可住人数
     */
    private Integer guestNumber;

    /**
     * 床型(1:单人床，2:双人床)
     */
    private Integer bedType;

    /**
     * 客房状态(0:空闲，1:已预订，2:已入住，3:未清洁)
     */
    private Integer status;

    /**
     * 最低房间价格
     */
    private BigDecimal lowPrice;

    /**
     * 最高房间价格
     */
    private BigDecimal highPrice;
}
