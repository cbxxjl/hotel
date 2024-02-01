package com.cbxjl.hotel.common.core.domain.dos;

import com.cbxjl.hotel.common.core.domain.dto.UserInfoDTO;
import com.cbxjl.hotel.common.core.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 用户领域对象
 *
 * @author : cbxjl
 * @date : 2024/2/1 11:18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class UserDO {
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户类型（1：管理员，2：员工）
     */
    private Integer userType;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 性别:0-男，1-女
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态（0：冻结，1：正常）
     */
    private Integer status;

    /**
     * 逻辑删除
     */
    private Integer delFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    public User doToPo() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

    public UserInfoDTO doToInfo() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(this, userInfoDTO);
        if (this.getUserType() == 1) {
            userInfoDTO.setUserTypeStr("管理员");
        } else if (this.getUserType() == 2) {
            userInfoDTO.setUserTypeStr("普通员工");
        }else if (this.getUserType() == 3) {
            userInfoDTO.setUserTypeStr("用户");
        }else {
            log.error("未知的用户类型，{}", this.getUserType());
        }
        return userInfoDTO;
    }
}
