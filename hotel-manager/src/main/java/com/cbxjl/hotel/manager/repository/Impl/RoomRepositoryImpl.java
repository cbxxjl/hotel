package com.cbxjl.hotel.manager.repository.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.utils.StringUtils;
import com.cbxjl.hotel.manager.core.dto.RoomPageParam;
import com.cbxjl.hotel.manager.core.entity.Room;
import com.cbxjl.hotel.manager.mapper.RoomMapper;
import com.cbxjl.hotel.manager.mapper.UserMapper;
import com.cbxjl.hotel.manager.repository.RoomRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Struct;

/**
 * 客房仓储实现类
 *
 * @author : cbxjl
 * @date : 2024/3/12 11:39
 */
@Repository
public class RoomRepositoryImpl implements RoomRepository {
    @Resource
    private RoomMapper roomMapper;

    /**
     * 客房分页查询
     *
     * @param roomPageParam 分页查询参数
     * @return 查询结果
     */
    @Override
    public Page<Room> page(RoomPageParam roomPageParam) {
        Page<Room> page = new Page<>(roomPageParam.getPageNum(), roomPageParam.getPageSize());
        LambdaQueryWrapper<Room> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(roomPageParam.getNumber()), Room::getNumber, roomPageParam.getNumber());
        queryWrapper.eq(roomPageParam.getType() != null, Room::getType, roomPageParam.getType());
        queryWrapper.eq(roomPageParam.getGuestNumber() != null, Room::getGuestNumber, roomPageParam.getGuestNumber());
        queryWrapper.eq(roomPageParam.getBedType() != null, Room::getBedType, roomPageParam.getBedType());
        queryWrapper.eq(roomPageParam.getStatus() != null, Room::getStatus, roomPageParam.getStatus());
        queryWrapper.ge(roomPageParam.getLowPrice() != null, Room::getPrice, roomPageParam.getLowPrice());
        queryWrapper.le(roomPageParam.getHighPrice() != null, Room::getPrice, roomPageParam.getHighPrice());
        queryWrapper.orderByDesc(Room::getCreateTime);

        return roomMapper.selectPage(page, queryWrapper);
    }
}
