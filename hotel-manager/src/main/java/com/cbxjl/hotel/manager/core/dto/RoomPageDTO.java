package com.cbxjl.hotel.manager.core.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 客房分页DTO
 *
 * @author : cbxjl
 * @date : 2024/3/12 11:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomPageDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 房间编号
     */
    private String number;

    /**
     * 房间类型（0-平价房，1-豪华房，2-商务，3-无障碍房）
     */
    private Integer type;
    private String typeStr;

    /**
     * 面积
     */
    private Integer area;

    /**
     * 可住人数
     */
    private Integer guestNumber;

    /**
     * 房间价格
     */
    private BigDecimal price;

    /**
     * 客房状态(0:空闲，1:已预订，2:已入住，3:未清洁)
     */
    private Integer status;
    private String statusStr;

    /**
     * 床型(1:单人床，2:双人床)
     */
    private Integer bedType;
    private String bedTypeStr;

    /**
     * 床数量
     */
    private Integer bedNumber;

    /**
     * 设施说明
     */
    private String installation;

    /**
     * 备注
     */
    private String describe;
}
