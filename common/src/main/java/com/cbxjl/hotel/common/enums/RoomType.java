package com.cbxjl.hotel.common.enums;

import com.cbxjl.hotel.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 房间类型
 *
 * @author : cbxjl
 * @date : 2024/3/12 15:06
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum RoomType {
    parity(0, "平价房"),
    luxury(1, "豪华房"),
    BUSINESS(2, "商务房"),
    ACCESSIBILITY(3, "无障碍房"),
            ;
    private Integer num;
    private String userType;

    public static String getType(Integer num) {
        for (RoomType value : RoomType.values()) {
            if (value.getNum().equals(num)) {
                return value.getUserType();
            }
        }
        log.error("不存在的客房类型：{}", num);
        throw new BusinessException("不存在的客房类型");
    }
}
