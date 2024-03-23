package com.cbxjl.hotel.manager.repository;

import com.cbxjl.hotel.manager.core.dos.CheckInRecordDO;

/**
 * 入住管理 仓储
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:27
 */
public interface CheckInRepository {
    /**
     * 办理入住
     *
     * @param checkInRecordDO 入住信息
     */
    void add(CheckInRecordDO checkInRecordDO);
}
