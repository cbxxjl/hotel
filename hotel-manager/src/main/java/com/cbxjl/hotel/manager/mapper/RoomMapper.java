package com.cbxjl.hotel.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbxjl.hotel.manager.core.entity.Room;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : cbxjl
 * @date : 2024/3/16 15:10
 */
@Mapper
public interface RoomMapper extends BaseMapper<Room> {
    int updateBatch(List<Room> list);

    int batchInsert(@Param("list") List<Room> list);

    List<String> getFloor();
}