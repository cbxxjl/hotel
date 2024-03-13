package com.cbxjl.hotel.manager.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 入住记录
 *
 * @author : cbxjl
 * @date : 2024/3/11 18:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "check_in_record")
public class CheckInRecord {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 住户id
     */
    @TableField(value = "guest_id")
    private Integer guestId;

    /**
     * 房间id
     */
    @TableField(value = "room_id")
    private Integer roomId;

    /**
     * 入住时间
     */
    @TableField(value = "check_in_time")
    private Date checkInTime;

    /**
     * 退房时间
     */
    @TableField(value = "check_out_time")
    private Date checkOutTime;

    /**
     * 实际退房时间
     */
    @TableField(value = "check_out_real_time")
    private Date checkOutRealTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;
}