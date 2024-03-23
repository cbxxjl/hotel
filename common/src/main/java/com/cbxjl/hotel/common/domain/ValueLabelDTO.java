package com.cbxjl.hotel.common.domain;

import cn.hutool.captcha.LineCaptcha;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : cbxjl
 * @date : 2024/3/19 15:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValueLabelDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Object value;

    private Object label;

    private List<ValueLabelDTO> children;
}
