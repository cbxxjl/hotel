package com.cbxjl.hotel.manager.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**
    * @author : cbxjl
    * @date : 2024/3/11 15:34
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "room_statistics")
public class RoomStatistics {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 房间总数
     */
    @TableField(value = "room_all_number")
    private Integer roomAllNumber;

    /**
     * 空房数量
     */
    @TableField(value = "room_free_number")
    private Integer roomFreeNumber;

    /**
     * 房间类型
     */
    @TableField(value = "room_type")
    private Boolean roomType;

    /**
     * 房间价格
     */
    @TableField(value = "room_price")
    private BigDecimal roomPrice;

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

    /**
     * 逻辑删除
     */
    @TableField(value = "del_flag")
    private Boolean delFlag;
}