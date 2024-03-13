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
 * 客户
 *
 * @author : cbxjl
 * @date : 2024/3/11 15:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tenant")
public class Guest {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 真实姓名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 身份证号码
     */
    @TableField(value = "card_num")
    private String cardNum;

    /**
     * 级别
     */
    @TableField(value = "`level`")
    private Boolean level;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

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