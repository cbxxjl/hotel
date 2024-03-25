package com.cbxjl.hotel.manager.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.manager.core.dos.GuestDO;
import com.cbxjl.hotel.manager.core.dos.RoomDO;
import com.cbxjl.hotel.manager.core.dto.GuestPageDTO;
import com.cbxjl.hotel.manager.core.dto.GuestPageParam;
import com.cbxjl.hotel.manager.core.entity.Guest;
import com.cbxjl.hotel.manager.repository.CheckInRepository;
import com.cbxjl.hotel.manager.repository.GuestRepository;
import com.cbxjl.hotel.manager.repository.RoomRepository;
import com.cbxjl.hotel.manager.service.GuestService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户 服务实现类
 *
 * @author : cbxjl
 * @date : 2024/3/23 14:20
 */
@Service
public class GuestServiceImpl implements GuestService {
    @Resource
    private GuestRepository guestRepository;
    @Resource
    private CheckInRepository checkInRepository;

    /**
     * 客户分页查询
     *
     * @param param 分页查询参数
     * @return 分页查询结果
     */
    @Override
    public PageResult<GuestPageDTO> listPage(GuestPageParam param) {
        Page<Guest> page = guestRepository.listPage(param);
        List<Guest> records = page.getRecords();
        List<GuestDO> guestDOList = records.stream().map(Guest::poToDo).collect(Collectors.toList());

        List<GuestPageDTO> pageDTOList = guestDOList.stream().map(item -> {
            GuestPageDTO guestPageDTO = item.doToPage();
            String roomNumber = checkInRepository.getRoomByGuestId(item.getId());
            Date recentCheckInTime = checkInRepository.getRecentCheckInTimeByGuestId(item.getId());
            guestPageDTO.setRoomNumber(roomNumber);
            guestPageDTO.setRecentCheckInTime(recentCheckInTime);

            return guestPageDTO;
        }).collect(Collectors.toList());

        return new PageResult<>(pageDTOList, page.getTotal(), page.getSize(), page.getCurrent());
    }
}
