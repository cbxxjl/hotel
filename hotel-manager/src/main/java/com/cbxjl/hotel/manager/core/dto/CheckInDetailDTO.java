package com.cbxjl.hotel.manager.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 入住详情DTO
 *
 * @author : cbxjl
 * @date : 2024/3/24 14:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInDetailDTO {
    /**
     * 房间号
     */
    private String roomNumber;

    /**
     * 房间类型
     */
    private String roomTypeStr;

    /**
     * 房间面积
     */
    private Integer area;

    /**
     * 房间可住人数
     */
    private Integer guestNum;

    /**
     * 入住时间
     */
    private Date checkInTime;

    /**
     * 退房时间
     */
    private Date checkOutTime;

    /**
     * 房客列表
     */
    private List<GuestPageDTO> guests;
}
