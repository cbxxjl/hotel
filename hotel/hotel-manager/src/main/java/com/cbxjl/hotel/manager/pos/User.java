package com.cbxjl.hotel.manager.pos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**
    * @author : cbxjl
    * @date : 2024/1/26 10:55
    */
/**
    * 酒店管理人员表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`user`")
public class User {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户类型（1：管理员，2：员工）
     */
    @TableField(value = "user_type")
    private Boolean userType;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private Boolean sex;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 状态（0：冻结，1：正常）
     */
    @TableField(value = "`status`")
    private Boolean status;

    /**
     * 逻辑删除
     */
    @TableField(value = "del_flag")
    private Boolean delFlag;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}