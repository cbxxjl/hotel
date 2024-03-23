package com.cbxjl.hotel.manager.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.domain.KeyValueDTO;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.common.domain.ValueLabelDTO;
import com.cbxjl.hotel.manager.core.dos.RoomDO;
import com.cbxjl.hotel.manager.core.dto.RoomAddDTO;
import com.cbxjl.hotel.manager.core.dto.RoomEditDTO;
import com.cbxjl.hotel.manager.core.dto.RoomPageDTO;
import com.cbxjl.hotel.manager.core.dto.RoomPageParam;
import com.cbxjl.hotel.manager.core.entity.Room;
import com.cbxjl.hotel.manager.repository.RoomRepository;
import com.cbxjl.hotel.manager.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public void update(RoomEditDTO roomEditDTO) {
        RoomDO roomDO = roomEditDTO.editToDo();
        roomRepository.update(roomDO);
    }

    /**
     * （批量） 删除
     *
     * @param ids 房间id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> ids) {
        roomRepository.delete(ids);
    }

    /**
     * 获取房间列表
     *
     * @param status 房间状态（0：空闲，1：已预定，2；已入住，3；未清洁，4：查询全部）
     * @return 房间列表
     */
    @Override
    public List<ValueLabelDTO> getFloorWithRoomList(Integer status) {
        //所有房间
        List<RoomDO> roomList = roomRepository.getRoomList(status);
        //所有楼层
        List<String> floorList = roomRepository.getFloor();
        Map<String, List<ValueLabelDTO>> map = new HashMap<>();

        //将楼层对应的房间map设置好
        floorList.forEach(item -> {
            map.put(item, new ArrayList<>());
        });

        //将楼层对应到键中，然后添加到对应的列表中
        roomList.forEach(item -> {
            String roomNumber = item.getNumber();
            String floor = roomNumber.split("-")[0];
            map.get(floor).add(new ValueLabelDTO(item.getNumber(), item.getNumber(), null));
        });

        //设置为二级菜单形式
        return floorList.stream().map(item -> {
            List<ValueLabelDTO> roomsForFloor = map.get(item);
            return new ValueLabelDTO(item, item + "楼", roomsForFloor);
        }).collect(Collectors.toList());
    }
}
