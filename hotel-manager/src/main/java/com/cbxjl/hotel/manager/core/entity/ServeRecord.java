package com.cbxjl.hotel.manager.core.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import com.cbxjl.hotel.manager.core.dos.ServeRecordDO;import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;import org.springframework.beans.BeanUtils;

/**
 * @author : cbxjl
 * @date : 2024/3/20 16:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "serve_record")
public class ServeRecord {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 员工id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 服务类型（0-清洁 1-送餐 2-补充房间物品）
     */
    @TableField(value = "serve_type")
    private Integer serveType;

    /**
     * 房间号
     */
    @TableField(value = "room_number")
    private String roomNumber;

    /**
     * 服务开始时间
     */
    @TableField(value = "serve_start")
    private Date serveStart;

    /**
     * 服务结束时间
     */
    @TableField(value = "serve_end")
    private Date serveEnd;

    /**
     * 服务状态（0-未开始 1-进行中 2-已完成）
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "`describe`")
    private String describe;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public ServeRecordDO poToDo() {
        ServeRecordDO serveRecordDO = new ServeRecordDO();
        BeanUtils.copyProperties(this, serveRecordDO);

        return serveRecordDO;
    }
}