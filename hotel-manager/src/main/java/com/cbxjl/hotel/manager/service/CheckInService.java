package com.cbxjl.hotel.manager.service;

import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.manager.core.dto.*;

import java.util.Map;

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

    /**
     * 入住管理分页查询
     *
     * @param checkInPageParam 分页查询参数
     * @return 分页查询结果
     */
    PageResult<CheckInPageDTO> listPage(CheckInPageParam checkInPageParam);

    /**
     * 根据房间号查询入住信息
     *
     * @param roomNumber 房间号
     * @return 入住信息
     */
    CheckInDetailDTO getCheckInByRoom(String roomNumber);

    /**
     * 办理换房
     *
     * @param changeRoomDTO 换房
     */
    void changeRoom(ChangeRoomDTO changeRoomDTO);

    /**
     * 退房
     *
     * @param roomNumber 房间号
     */
    void checkOut(String roomNumber);
}
