package com.cbxjl.hotel.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbxjl.hotel.manager.core.entity.User;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : cbxjl
 * @date : 2024/3/13 19:07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    int updateBatch(List<User> list);

    int batchInsert(@Param("list") List<User> list);
}