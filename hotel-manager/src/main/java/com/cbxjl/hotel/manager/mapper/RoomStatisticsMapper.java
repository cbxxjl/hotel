package com.cbxjl.hotel.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbxjl.hotel.manager.core.entity.RoomStatistics;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

 /**
    * @author : cbxjl
    * @date : 2024/3/11 15:34
    */
 @Mapper
public interface RoomStatisticsMapper extends BaseMapper<RoomStatistics> {
    int updateBatch(List<RoomStatistics> list);

    int batchInsert(@Param("list") List<RoomStatistics> list);
}