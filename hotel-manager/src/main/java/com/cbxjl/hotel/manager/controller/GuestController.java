package com.cbxjl.hotel.manager.controller;

import com.cbxjl.hotel.manager.service.GuestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 客户相关接口
 *
 * @author : cbxjl
 * @date : 2024/3/23 14:18
 */
@RestController
@RequestMapping("/hotel/guest")
public class GuestController {
    @Resource
    private GuestService guestService;


}
