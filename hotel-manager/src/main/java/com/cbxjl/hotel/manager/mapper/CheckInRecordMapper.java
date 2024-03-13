package com.cbxjl.hotel.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbxjl.hotel.manager.core.entity.CheckInRecord;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : cbxjl
 * @date : 2024/3/11 18:46
 */
@Mapper
public interface CheckInRecordMapper extends BaseMapper<CheckInRecord> {
    int updateBatch(List<CheckInRecord> list);

    int batchInsert(@Param("list") List<CheckInRecord> list);
}