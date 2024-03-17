package com.cbxjl.hotel.manager.service;

import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.manager.core.dto.RoomAddDTO;
import com.cbxjl.hotel.manager.core.dto.RoomEditDTO;
import com.cbxjl.hotel.manager.core.dto.RoomPageDTO;
import com.cbxjl.hotel.manager.core.dto.RoomPageParam;

/**
 * 客房服务
 *
 * @author : cbxjl
 * @date : 2024/3/12 11:40
 */

public interface RoomService {
    /**
     * 客房分页查询
     *
     * @param roomPageParam 分页查询参数
     * @return 查询结果
     */
    PageResult<RoomPageDTO> listPage(RoomPageParam roomPageParam);

    /**
     * 添加客房
     *
     * @param roomAddDTO 客房添加DTO
     */
    void add(RoomAddDTO roomAddDTO);

    /**
     * 根据房间ID获取房间信息
     *
     * @param id 房间id
     * @return 房间信息
     */
    RoomEditDTO getById(Long id);

    /**
     * 更改房间信息
     *
     * @param roomEditDTO 房间编辑DTO
     */
    void update(RoomEditDTO roomEditDTO);
}
