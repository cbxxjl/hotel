package com.cbxjl.hotel.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.cbxjl.hotel.common.core.domain.dos.UserDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
    * @author : cbxjl
    * @date : 2024/1/29 15:46
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
    private Integer userType;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 性别:0-男，1-女
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 状态（0：冻结，1：正常）
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 逻辑删除
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

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

    public UserDO poToDo() {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(this, userDO);
        return userDO;
    }
}