package com.cbxjl.hotel.manager.core.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 客房服务记录
 *
 * @author : cbxjl
 * @date : 2024/3/18 16:15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServeRecordPageDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 员工id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String userName;

    /**
     * 服务类型（0-清洁 1-送餐 2-补充房间物品）
     */
    private Integer serveType;
    private String serveTypeStr;

    /**
     * 房间号
     */
    private String roomNumber;
    /**
     * 服务状态（0-未开始 1-进行中 2-已完成）
     */
    private Integer status;
    private String statusStr;

    /**
     * 服务开始时间
     */
    private Date serveStart;

    /**
     * 服务结束时间
     */
    private Date serveEnd;

    /**
     * 备注
     */
    private String describe;
}
