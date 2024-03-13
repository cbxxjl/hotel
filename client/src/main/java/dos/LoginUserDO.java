package dos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 当前登录用户do
 *
 * @author : cbxjl
 * @date : 2024/2/28 15:17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class LoginUserDO {
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户类型（1：管理员，2：前台，3：财务，4：后勤）
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
}
