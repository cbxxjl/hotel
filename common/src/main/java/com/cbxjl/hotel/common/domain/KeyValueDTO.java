package com.cbxjl.hotel.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * key-value通用
 *
 * @author : cbxjl
 * @date : 2024/3/8 16:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValueDTO {
    private Object key;

    private Object value;
}
