package com.cbxjl.hotel.common.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbxjl.hotel.common.core.domain.entity.User;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

 /**
    * @author : cbxjl
    * @date : 2024/1/29 15:46
    */
 @Mapper
public interface UserMapper extends BaseMapper<User> {
    int updateBatch(List<User> list);

    int batchInsert(@Param("list") List<User> list);
}