package com.cbxjl.hotel.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbxjl.hotel.manager.core.entity.Guest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : cbxjl
 * @date : 2024/3/21 15:48
 */
@Mapper
public interface GuestMapper extends BaseMapper<Guest> {
    int updateBatch(List<Guest> list);

    int batchInsert(@Param("list") List<Guest> list);
}