package com.cbxjl.hotel.manager.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.common.exception.BusinessException;
import com.cbxjl.hotel.manager.core.dos.RoomDO;
import com.cbxjl.hotel.manager.core.dto.RoomAddDTO;
import com.cbxjl.hotel.manager.core.dto.RoomEditDTO;
import com.cbxjl.hotel.manager.core.dto.RoomPageDTO;
import com.cbxjl.hotel.manager.core.dto.RoomPageParam;
import com.cbxjl.hotel.manager.core.entity.Room;
import com.cbxjl.hotel.manager.repository.RoomRepository;
import com.cbxjl.hotel.manager.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客房服务实现类
 *
 * @author : cbxjl
 * @date : 2024/3/12 11:42
 */
@Service
@Slf4j
public class RoomServiceImpl implements RoomService {
    @Resource
    private RoomRepository roomRepository;

    /**
     * 客房分页查询
     *
     * @param roomPageParam 分页查询参数
     * @return 查询结果
     */
    @Override
    public PageResult<RoomPageDTO> listPage(RoomPageParam roomPageParam) {
        Page<Room> page = roomRepository.page(roomPageParam);
        List<Room> records = page.getRecords();
        List<RoomDO> roomDOList = records.stream().map(Room::poToDO).collect(Collectors.toList());
        List<RoomPageDTO> pageDTOList = roomDOList.stream().map(RoomDO::doToPage).collect(Collectors.toList());

        return new PageResult<>(pageDTOList, page.getTotal(), page.getSize(), page.getCurrent());
    }

    /**
     * 添加客房
     *
     * @param roomAddDTO 客房添加DTO
     */
    @Override
    public void add(RoomAddDTO roomAddDTO) {
        RoomDO roomDO = roomAddDTO.addToDO();
        roomRepository.checkRoomByNumber(roomDO.getNumber());
        roomRepository.add(roomDO);
    }

    /**
     * 根据房间ID获取房间信息
     *
     * @param id 房间id
     * @return 房间信息
     */
    @Override
    public RoomEditDTO getById(Long id) {
        RoomDO roomDO = roomRepository.getById(id);
        return roomDO.doToEdit();
    }

    /**
     * 更改房间信息
     *
     * @param roomEditDTO 房间编辑DTO
     */
    @Override
    public void update(RoomEditDTO roomEditDTO) {
        RoomDO roomDO = roomEditDTO.editToDo();
        roomRepository.update(roomDO);
    }
}
