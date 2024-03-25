package com.cbxjl.hotel.manager.service.Impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.common.enums.RoomType;
import com.cbxjl.hotel.common.utils.StringUtils;
import com.cbxjl.hotel.manager.core.dos.CheckInRecordDO;
import com.cbxjl.hotel.manager.core.dos.GuestDO;
import com.cbxjl.hotel.manager.core.dos.RoomDO;
import com.cbxjl.hotel.manager.core.dto.*;
import com.cbxjl.hotel.manager.core.entity.CheckInRecord;
import com.cbxjl.hotel.manager.core.entity.Guest;
import com.cbxjl.hotel.manager.repository.CheckInRepository;
import com.cbxjl.hotel.manager.repository.GuestRepository;
import com.cbxjl.hotel.manager.repository.RoomRepository;
import com.cbxjl.hotel.manager.service.CheckInService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 入住管理服务实现类
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:26
 */
@Service
public class CheckInServiceImpl implements CheckInService {
    @Resource
    private CheckInRepository checkInRepository;
    @Resource
    private GuestRepository guestRepository;

    @Resource
    private RoomRepository roomRepository;

    /**
     * 办理入住
     *
     * @param checkInAddDTO 入住信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CheckInAddDTO checkInAddDTO) {
        //客户信息与入住信息分开保存
        List<GuestAddDTO> guests = checkInAddDTO.getGuests();
        List<GuestDO> guestDOList = guests.stream().map(GuestAddDTO::addToDo).collect(Collectors.toList());

        //只保存新用户，然后返回所有客户的id
        List<Long> guestIds = guestDOList.stream().map(item -> {
            GuestDO guestDO = guestRepository.getByCardNum(item.getCardNum());
            if (guestDO == null) {
                //说明是新用户，那么添加
                return guestRepository.add(item);
            } else {
                return guestDO.getId();
            }
        }).collect(Collectors.toList());

        //保存入住信息，先获取房客id
        CheckInRecordDO checkInRecordDO = checkInAddDTO.addToDo();
        checkInRecordDO.setGuestIds(guestIds.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(","))
        );
        checkInRepository.add(checkInRecordDO);

        //完成入住后，更新房间状态
        String roomNumber = checkInAddDTO.getRoomNumber();
        RoomDO roomDO = roomRepository.getRoomByNumber(roomNumber);
        roomDO.setStatus(2);
        roomRepository.update(roomDO);
    }

    /**
     * 入住管理分页查询
     *
     * @param checkInPageParam 分页查询参数
     * @return 分页查询结果
     */
    @Override
    public PageResult<CheckInPageDTO> listPage(CheckInPageParam checkInPageParam) {
        Page<CheckInRecordDO> page = checkInRepository.listPage(checkInPageParam);
        List<CheckInRecordDO> records = page.getRecords();
        List<CheckInPageDTO> checkInPageDTOList = records.stream().map(CheckInRecordDO::doToPage).collect(Collectors.toList());

        return new PageResult<>(checkInPageDTOList, page.getTotal(), page.getSize(), page.getCurrent());
    }

    /**
     * 根据房间号查询入住信息
     *
     * @param roomNumber 房间号
     * @return 入住信息
     */
    @Override
    public CheckInDetailDTO getCheckInByRoom(String roomNumber) {
        //根据房间号查询入住信息
        CheckInRecordDO checkInRecordDO = checkInRepository.getCheckInByRoom(roomNumber);
        CheckInDetailDTO checkInDetailDTO = checkInRecordDO.doToDetail();
        //获取住户Id
        List<Long> guestIds = StringUtils.splitToIds(checkInRecordDO.getGuestIds(), ",");
        List<GuestDO> guestList = guestRepository.getGuestById(guestIds);
        List<GuestPageDTO> pageDTOList = guestList.stream().map(GuestDO::doToPage).collect(Collectors.toList());

        //获取房间信息
        RoomDO roomDO = roomRepository.getRoomByNumber(roomNumber);

        checkInDetailDTO.setGuests(pageDTOList);
        checkInDetailDTO.setRoomTypeStr(RoomType.getType(roomDO.getType()));
        checkInDetailDTO.setArea(roomDO.getArea());
        checkInDetailDTO.setGuestNum(roomDO.getGuestNumber());

        return checkInDetailDTO;
    }

    /**
     * 办理换房
     *
     * @param changeRoomDTO 换房
     */
    @Override
    public void changeRoom(ChangeRoomDTO changeRoomDTO) {
        Long checkInId = changeRoomDTO.getCheckInId();
        CheckInRecordDO checkInRecordDO = checkInRepository.getCheckInById(checkInId);
        //todo 换房还没写好
    }

    /**
     * 退房
     *
     * @param roomNumber 房间号
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkOut(String roomNumber) {
        CheckInRecordDO checkInDO = checkInRepository.getCheckInByRoom(roomNumber);
        //设置实际退房时间
        checkInDO.setCheckOutRealTime(new Date());
        checkInRepository.update(checkInDO);

        //将房间状态设置为“未清扫”
        RoomDO roomDO = roomRepository.getRoomByNumber(roomNumber);
        roomDO.setStatus(3);
        roomRepository.update(roomDO);
    }
}
