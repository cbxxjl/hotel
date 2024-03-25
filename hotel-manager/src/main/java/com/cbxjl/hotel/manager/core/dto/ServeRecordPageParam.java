package com.cbxjl.hotel.manager.core.dto;

import com.cbxjl.hotel.common.domain.PageParams;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * 客房服务记录 分页参数
 *
 * @author : cbxjl
 * @date : 2024/3/18 16:21
 */
@Data
public class ServeRecordPageParam extends PageParams {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 服务类型（0-清洁 1-送餐 2-补充房间物品）
     */
    private Integer serveType;

    /**
     * 服务开始 起始时间
     */
    private Date serveStart;

    /**
     * 服务开始 结束时间
     */
    private Date serveEnd;

    /**
     * 查询当前-F/历史查询-T
     */
    private Boolean nowOrHistory;
}
