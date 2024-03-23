package com.cbxjl.hotel.common.enums;

import com.cbxjl.hotel.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务状态
 *
 * @author : cbxjl
 * @date : 2024/3/18 17:04
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum ServeStatus {
    NOSTART(0, "未开始"),
    ONGOING(1, "进行中"),

    FINISH(2, "已完成"),

    ;
    private Integer num;
    private String serveStatus;

    public static String getServeStatus(Integer num) {
        for (ServeStatus value : ServeStatus.values()) {
            if (value.num.equals(num)) {
                return value.serveStatus;
            }
        }
        log.error("错误的服务状态：{}", num);
        throw new BusinessException("错误的服务状态：" + num);
    }
}
