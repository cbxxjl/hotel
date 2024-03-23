package com.cbxjl.hotel.common.enums;

import com.cbxjl.hotel.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 客房服务类型
 *
 * @author : cbxjl
 * @date : 2024/3/18 16:39
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum ServeType {
    CLEAN(0, "清洁"),
    SUPFOOD(1, "送餐"),
    SUPITEM(2,"补充房间物品"),
    ;

    private Integer num;
    private String serveType;

    public static String getServeType(Integer num) {
        for (ServeType value : ServeType.values()) {
            if (value.num.equals(num)) {
                return value.getServeType();
            }
        }
        log.error("不存在的服务类型:{}", num);
        throw new BusinessException("不存在的服务类型:" + num);
    }
}
