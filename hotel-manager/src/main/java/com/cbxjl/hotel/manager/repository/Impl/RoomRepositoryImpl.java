package com.cbxjl.hotel.manager.repository.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.exception.BusinessException;
import com.cbxjl.hotel.common.utils.SnowIdUtils;
import com.cbxjl.hotel.common.utils.StringUtils;
import com.cbxjl.hotel.manager.core.dos.RoomDO;
import com.cbxjl.hotel.manager.core.dto.RoomPageParam;
import com.cbxjl.hotel.manager.core.entity.Room;
import com.cbxjl.hotel.manager.mapper.RoomMapper;
import com.cbxjl.hotel.manager.mapper.UserMapper;
import com.cbxjl.hotel.manager.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    /**
     * 添加客房
     *
     * @param roomDO 客房DO
     */
    @Override
    public void add(RoomDO roomDO) {
        Room room = roomDO.doToPO();
        room.setId(SnowIdUtils.uniqueLong());
        roomMapper.insert(room);
    }

    /**
     * 根据房间号检测是否重复
     *
     * @param number 房间号
     * @return 检查结果
     */
    @Override
    public void checkRoomByNumber(String number) {
        LambdaQueryWrapper<Room> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(number), Room::getNumber, number);
        Room room = roomMapper.selectOne(queryWrapper);
        if (room != null) {
            log.error("房间号已存在：{}", room.getNumber());
            throw new BusinessException("房间号已存在：" + room.getNumber());
        }
    }

    /**
     * 根据房间号查找房间
     *
     * @param number 房间号
     * @return 房间
     */
    @Override
    public RoomDO
    getRoomByNumber(String number) {
        LambdaQueryWrapper<Room> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(number), Room::getNumber, number);
        Room room = roomMapper.selectOne(queryWrapper);
        if (room == null) {
            log.error("当前房间号不存在：{}", number);
            throw new BusinessException("当前房间号不存在：" + number);
        }
        return room.poToDO();
    }

    /**
     * 根据房间id获取房间
     *
     * @param id 房间id
     * @return 房间
     */
    @Override
    public RoomDO getById(Long id) {
        Room room = roomMapper.selectById(id);
        return room.poToDO();
    }

    /**
     * 编辑房间信息
     *
     * @param roomDO 房间DO
     */
    @Override
    public void update(RoomDO roomDO) {
        Room room = roomDO.doToPO();
        roomMapper.updateById(room);
    }
}
