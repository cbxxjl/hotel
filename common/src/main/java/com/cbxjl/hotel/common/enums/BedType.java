package com.cbxjl.hotel.common.enums;

import com.cbxjl.hotel.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**床的类型
 * @author : cbxjl
 * @date : 2024/3/12 15:22
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum BedType {
    SINGLE(1, "单人床"),
    DOUBLE(2, "双人床"),

    ;

    private Integer num;
    private String type;

    public static String getType(Integer num) {
        for (BedType value : BedType.values()) {
            if (value.getNum().equals(num)) {
                return value.getType();
            }
        }
        log.error("错误的床的类型： {}", num);
        throw new BusinessException("错误的床的类型");
    }
}
