package com.cbxjl.hotel.manager.core.dto;

import com.cbxjl.hotel.common.domain.PageParams;
import lombok.Data;

import java.util.Date;

/**
 * 入住管理 分页查询参数
 *
 * @author : cbxjl
 * @date : 2024/3/23 16:23
 */
@Data
public class CheckInPageParam extends PageParams {

    /**
     * 房间号
     */
    private String roomNumber;

    /**
     * 房间类型（0-平价房，1-豪华房，2-商务，3-无障碍房）
     */
    private Integer roomType;

    /**
     * 0-到期未退房，1-未到期, 2-已退房
     */
    private Integer status;

    /**
     * 入住时间 起始
     */
    private Date checkInTimeStart;

    /**
     * 入住时间 结束
     */
    private Date checkInTimeEnd;

}
