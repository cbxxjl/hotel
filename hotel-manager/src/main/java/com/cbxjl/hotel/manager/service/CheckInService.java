package com.cbxjl.hotel.manager.service;

import com.cbxjl.hotel.manager.core.dto.CheckInAddDTO;

/**
 * 入住相关服务
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:26
 */
public interface CheckInService {
    /**
     * 办理入住
     *
     * @param checkInAddDTO 入住信息
     */
    void add(CheckInAddDTO checkInAddDTO);
}
