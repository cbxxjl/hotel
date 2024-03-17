package com.cbxjl.hotel.manager.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.manager.core.dos.RoomDO;
import com.cbxjl.hotel.manager.core.dto.RoomPageParam;
import com.cbxjl.hotel.manager.core.entity.Room;

/**
 * 客房仓储
 *
 * @author : cbxjl
 * @date : 2024/3/12 11:38
 */
public interface RoomRepository {
    /**
     * 客房分页查询
     *
     * @param roomPageParam 分页查询参数
     * @return 查询结果
     */
    Page<Room> page(RoomPageParam roomPageParam);

    /**
     * 添加客房
     *
     * @param roomDO 客房DO
     */
    void add(RoomDO roomDO);

    /**
     * 根据房间号检测是否重复
     *
     * @param number 房间号
     * @return 检查结果
     */
    void checkRoomByNumber(String number);

    /**
     * 根据房间号查找房间
     *
     * @param number 房间号
     * @return 房间
     */
    RoomDO getRoomByNumber(String number);

    /**
     * 根据房间id获取房间
     *
     * @param id 房间id
     * @return 房间
     */
    RoomDO getById(Long id);

    /**
     * 编辑房间信息
     *
     * @param roomDO 房间DO
     */
    void update(RoomDO roomDO);
}
