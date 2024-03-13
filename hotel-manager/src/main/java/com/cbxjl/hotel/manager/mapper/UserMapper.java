package com.cbxjl.hotel.manager.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbxjl.hotel.manager.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
    * @author : cbxjl
    * @date : 2024/1/29 15:46
    */
 @Mapper
public interface UserMapper extends BaseMapper<User> {
    int updateBatch(List<User> list);

    int batchInsert(@Param("list") List<User> list);
}