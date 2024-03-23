package com.cbxjl.hotel.manager.core.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import com.cbxjl.hotel.manager.core.dos.GuestDO;import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;import org.springframework.beans.BeanUtils;

/**
 * @author : cbxjl
 * @date : 2024/3/21 15:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "guest")
public class Guest {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 真实姓名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 性别（0-男，1-女）
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 身份证号码
     */
    @TableField(value = "card_num")
    private String cardNum;

    /**
     * 电话号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 级别
     */
    @TableField(value = "`level`")
    private Integer level;

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
     * 逻辑删除
     */
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private Boolean delFlag;

    public GuestDO poToDo() {
        GuestDO guestDO = new GuestDO();
        BeanUtils.copyProperties(this, guestDO);

        return guestDO;
    }
}