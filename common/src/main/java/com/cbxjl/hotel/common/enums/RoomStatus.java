package com.cbxjl.hotel.common.enums;

import com.cbxjl.hotel.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 房间状态
 *
 * @author : cbxjl
 * @date : 2024/3/12 15:14
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum RoomStatus {
    FREE(0 , "空闲"),
    BOOK(1 , "已预订"),
    CHECKEDIN(2 , "已入住"),
    UNCLEAN(3, "未清洁"),


    ;
    private Integer num;
    private String type;

    public static String getType(Integer num) {
        for (RoomStatus value : RoomStatus.values()) {
            if (value.getNum().equals(num)) {
                return value.getType();
            }
        }
        log.error("错误的房间状态：{}", num);
        throw new BusinessException("错误的房间类型");
    }
}
