package com.cbxjl.hotel.manager.core.dto;

import com.cbxjl.hotel.manager.core.dos.UserDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * 用户修改密码DTO
 *
 * @author : cbxjl
 * @date : 2024/3/11 16:36
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPwdDTO {
    Long id;

    String password;

    String newPassword;
}
