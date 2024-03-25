package com.cbxjl.hotel.manager.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 客户分页查询DTO
 *
 * @author : cbxjl
 * @date : 2024/3/23 14:27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestPageDTO {
    /**
     * 真实姓名
     */
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 性别（0-男，1-女）
     */
    private Integer sex;
    private String sexStr;

    /**
     * 级别
     */
    private Integer level;
    private String levelStr;

    /**
     * 房间号，入住酒店显示房间号，否则显示未入住
     */
    private String roomNumber;

    /**
     * 身份证号码
     */
    private String cardNum;

    /**
     * 最近一次入住时间
     */
    private Date recentCheckInTime;
}
