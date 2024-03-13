package com.cbxjl.hotel.manager.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
}
