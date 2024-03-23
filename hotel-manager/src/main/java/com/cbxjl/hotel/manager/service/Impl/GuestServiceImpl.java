package com.cbxjl.hotel.manager.service.Impl;

import com.cbxjl.hotel.manager.core.entity.Guest;
import com.cbxjl.hotel.manager.repository.GuestRepository;
import com.cbxjl.hotel.manager.service.GuestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
