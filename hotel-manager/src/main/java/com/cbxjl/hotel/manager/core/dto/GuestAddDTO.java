package com.cbxjl.hotel.manager.core.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cbxjl.hotel.manager.core.dos.GuestDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 客户增加 DTO
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestAddDTO {
    /**
     * 真实姓名
     */
    @NotBlank(message = "客户姓名不能为空")
    private String name;

    /**
     * 身份证号码
     */
    @NotBlank(message = "身份证号码不能为空")
    private String cardNum;

    /**
     * 电话号码
     */
    @NotBlank(message = "电话号码不能为空")
    private String phone;
    /**
     * 性别（0-男，1-女）
     */
    @NotNull(message = "性别不能为空")
    private Integer sex;

    public GuestDO addToDo() {
        GuestDO guestDO = new GuestDO();
        BeanUtils.copyProperties(this, guestDO);

        return guestDO;
    }
}
