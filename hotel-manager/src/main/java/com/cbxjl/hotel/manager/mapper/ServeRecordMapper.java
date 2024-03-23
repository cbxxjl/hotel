package com.cbxjl.hotel.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbxjl.hotel.manager.core.entity.ServeRecord;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : cbxjl
 * @date : 2024/3/20 16:13
 */
@Mapper
public interface ServeRecordMapper extends BaseMapper<ServeRecord> {
    int updateBatch(List<ServeRecord> list);

    int batchInsert(@Param("list") List<ServeRecord> list);
}