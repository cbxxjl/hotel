package com.cbxjl.hotel.manager.core.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import com.cbxjl.hotel.manager.core.dos.UserDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * 酒店管理人员表
 *
 * @author : cbxjl
 * @date : 2024/1/29 15:46
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
     * 用户类型（1：管理员，2：前台，3：财务，4：后勤）
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
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private Integer delFlag;

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
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public UserDO poToDo() {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(this, userDO);
        return userDO;
    }
}