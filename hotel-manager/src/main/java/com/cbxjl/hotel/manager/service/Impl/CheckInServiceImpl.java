package com.cbxjl.hotel.manager.service.Impl;

import com.cbxjl.hotel.manager.core.dos.CheckInRecordDO;
import com.cbxjl.hotel.manager.core.dos.GuestDO;
import com.cbxjl.hotel.manager.core.dto.CheckInAddDTO;
import com.cbxjl.hotel.manager.core.dto.GuestAddDTO;
import com.cbxjl.hotel.manager.core.entity.Guest;
import com.cbxjl.hotel.manager.repository.CheckInRepository;
import com.cbxjl.hotel.manager.repository.GuestRepository;
import com.cbxjl.hotel.manager.service.CheckInService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
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
    }
}
