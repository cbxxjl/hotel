package com.cbxjl.hotel.manager.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 入住 分页查询DTO
 *
 * @author : cbxjl
 * @date : 2024/3/23 16:34
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInPageDTO {
    /**
     * 房间号
     */
    private String roomNumber;

    /**
     * 入住时间
     */
    private Date checkInTime;

    /**
     * 退房时间
     */
    private Date checkOutTime;

    /**
     * 实际退房时间
     */
    private Date checkOutRealTime;

    /**
     * 入住人数
     */
    private Integer guestNum;

    /**
     * 费用
     */
    private BigDecimal pay;
}
