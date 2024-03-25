package com.cbxjl.hotel.manager.core.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import com.cbxjl.hotel.manager.core.dos.CheckInRecordDO;import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;import org.springframework.beans.BeanUtils;

/**
 * @author : cbxjl
 * @date : 2024/3/20 16:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "check_in_record")
public class CheckInRecord {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 房客ids
     */
    @TableField(value = "guest_ids")
    private String guestIds;

    /**
     * 房间号
     */
    @TableField(value = "room_number")
    private String roomNumber;

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
     * 费用
     */
    @TableField(value = "pay")
    private BigDecimal pay;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    public CheckInRecordDO poToDo() {
        CheckInRecordDO checkInRecordDO = new CheckInRecordDO();
        BeanUtils.copyProperties(this, checkInRecordDO);

        return checkInRecordDO;
    }
}