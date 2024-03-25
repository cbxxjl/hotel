package com.cbxjl.hotel.manager.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.manager.core.dos.CheckInRecordDO;
import com.cbxjl.hotel.manager.core.dos.GuestDO;
import com.cbxjl.hotel.manager.core.dos.RoomDO;
import com.cbxjl.hotel.manager.core.dto.CheckInPageParam;
import com.cbxjl.hotel.manager.core.entity.CheckInRecord;

import java.util.Date;
import java.util.List;

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

    /**
     * 根据客户id查询房间号
     *
     * @param id 客户id
     * @return 如果没有则返回 "未入住"
     */
    String getRoomByGuestId(Long id);

    /**
     * 根据用户id获取最近一次的入住时间
     *
     * @param id 客户id
     * @return 最近一次的入住时间
     */
    Date getRecentCheckInTimeByGuestId(Long id);

    /**
     * 入住管理 分页查询
     *
     * @param checkInPageParam 分页查询参数
     * @return 分页结果
     */
    Page<CheckInRecordDO> listPage(CheckInPageParam checkInPageParam);

    /**
     * 根据房间号查询入住信息
     *
     * @param roomNumber 房间号
     * @return 入住信息
     */
    CheckInRecordDO getCheckInByRoom(String roomNumber);

    /**
     * 通过id获取入住记录
     *
     * @param checkInId 入住记录id
     * @return 入住记录
     */
    CheckInRecordDO getCheckInById(Long checkInId);

    /**
     * 更新入住信息
     *
     * @param checkInDO 入住信息
     */
    void update(CheckInRecordDO checkInDO);
}
