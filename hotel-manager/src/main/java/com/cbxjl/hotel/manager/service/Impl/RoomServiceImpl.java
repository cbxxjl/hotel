package com.cbxjl.hotel.manager.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.manager.core.dos.RoomDO;
import com.cbxjl.hotel.manager.core.dto.RoomPageDTO;
import com.cbxjl.hotel.manager.core.dto.RoomPageParam;
import com.cbxjl.hotel.manager.core.entity.Room;
import com.cbxjl.hotel.manager.repository.RoomRepository;
import com.cbxjl.hotel.manager.service.RoomService;
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
}
