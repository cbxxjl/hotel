package com.cbxjl.hotel.manager.core.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import com.cbxjl.hotel.manager.core.dos.RoomDO;import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;import org.springframework.beans.BeanUtils;

/**
 * @author : cbxjl
 * @date : 2024/3/16 15:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "room")
public class Room {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 房间类型（0-平价房，1-豪华房，2-商务，3-无障碍房）
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 房间价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 房间编号
     */
    @TableField(value = "`number`")
    private String number;

    /**
     * 面积
     */
    @TableField(value = "area")
    private Integer area;

    /**
     * 床型(1:单人床，2:双人床)
     */
    @TableField(value = "bed_type")
    private Integer bedType;

    /**
     * 床数量
     */
    @TableField(value = "bed_number")
    private Integer bedNumber;

    /**
     * 可住人数
     */
    @TableField(value = "guest_number")
    private Integer guestNumber;

    /**
     * 房间图片
     */
    @TableField(value = "img")
    private String img;

    /**
     * 客房状态(0:空闲，1:已预订，2:已入住，3:未清洁)
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 设施说明
     */
    @TableField(value = "installation")
    private String installation;

    /**
     * 备注
     */
    @TableField(value = "`describe`")
    private String describe;

    public RoomDO poToDO() {
        RoomDO roomDO = new RoomDO();
        BeanUtils.copyProperties(this, roomDO);

        return roomDO;
    }
}