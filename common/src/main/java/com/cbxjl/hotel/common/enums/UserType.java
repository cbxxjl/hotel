package com.cbxjl.hotel.common.enums;

import com.cbxjl.hotel.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户类型
 *
 * @author : cbxjl
 * @date : 2024/2/28 14:52
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum UserType {
    ADMINISTRATOR(1, "管理员"),
    RECEPTIONDESK(2, "前台"),
    FINANCE(3, "财务"),
    LOGISTICS(4, "后勤")
    ;
    private Integer num;
    private String userType;

    public static String getUserType(Integer num) {
        for (UserType value : UserType.values()) {
            if (value.getNum().equals(num)) {
                return value.getUserType();
            }
        }
        log.error("不存在的用户类型: {}", num);
        throw new BusinessException("不存在的用户类型:" + num);
    }
}
