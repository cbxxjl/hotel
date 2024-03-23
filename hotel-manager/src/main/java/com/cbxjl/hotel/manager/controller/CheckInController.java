package com.cbxjl.hotel.manager.controller;

import com.cbxjl.hotel.common.annotations.CheckUserType;
import com.cbxjl.hotel.common.constant.UserTypeConstants;
import com.cbxjl.hotel.common.domain.R;
import com.cbxjl.hotel.manager.core.dto.CheckInAddDTO;
import com.cbxjl.hotel.manager.service.CheckInService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 入住管理 相关接口
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:24
 */
@RestController
@RequestMapping("/hotel/check")
public class CheckInController {
    @Resource
    private CheckInService checkInService;

    /**
     * 办理入住
     *
     * @param checkInAddDTO 入住信息
     * @return 办理结果
     */
    @PostMapping("/add")
    @CheckUserType(value = {UserTypeConstants.RECEPTIONDESK, UserTypeConstants.ADMINISTRATOR})
    public R<Void> add(@RequestBody CheckInAddDTO checkInAddDTO) {
        checkInService.add(checkInAddDTO);
        return R.ok();
    }
}
