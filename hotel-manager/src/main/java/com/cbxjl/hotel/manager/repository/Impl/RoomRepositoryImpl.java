package com.cbxjl.hotel.manager.repository.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.enums.RoomStatus;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * （批量） 删除
     *
     * @param ids 房间id
     */
    @Override
    public void delete(List<Long> ids) {
        List<Room> rooms = roomMapper.selectBatchIds(ids);
        rooms.forEach(item -> {
            //已预定 已入住 的房间不允许删除
            if (item.getStatus() == 1 || item.getStatus() == 2) {
                log.error("已预定/已入住的房间不允许删除");
                throw new BusinessException("已预定/已入住的房间不允许删除");
            }
        });
        roomMapper.deleteBatchIds(ids);
    }

    /**
     * 获取房间列表
     *
     * @param status 房间状态（0：空闲，1：已预定，2；已入住，3；未清洁，4：查询全部）
     * @return 房间列表
     */
    @Override
    public List<RoomDO> getRoomList(Integer status) {
        LambdaQueryWrapper<Room> queryWrapper = new LambdaQueryWrapper<>();
        List<Room> rooms;
        //如果不是查询全部，那么按状态查询
        if (status != 4) {
            queryWrapper.eq(Room::getStatus, status);
            rooms = roomMapper.selectList(queryWrapper);
            if (rooms.isEmpty()) {
                log.error("房间列表为空");
                throw new BusinessException("\"" + RoomStatus.getType(status) + "\"的房间没有了");
            }
        }else {
            rooms = roomMapper.selectList(queryWrapper);
        }
        return rooms.stream().map(Room::poToDO).collect(Collectors.toList());
    }

    /**
     * 获取所有楼层
     *
     * @return 楼层列表
     */
    @Override
    public List<String> getFloor() {
        List<String> floorList = roomMapper.getFloor();
        return floorList;
    }
}
