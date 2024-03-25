package com.cbxjl.hotel.manager.controller;

import com.cbxjl.hotel.common.annotations.CheckUserType;
import com.cbxjl.hotel.common.constant.UserTypeConstants;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.common.domain.R;
import com.cbxjl.hotel.manager.core.dto.GuestPageDTO;
import com.cbxjl.hotel.manager.core.dto.GuestPageParam;
import com.cbxjl.hotel.manager.service.GuestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 酒店客户分页查询
     *
     * @param param 分页查询参数
     * @return 分页查询结果
     */
    @PostMapping("/list")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR, UserTypeConstants.RECEPTIONDESK})
    public R<PageResult<GuestPageDTO>> listPage(@RequestBody GuestPageParam param) {
        PageResult<GuestPageDTO> pageResult = guestService.listPage(param);
        return R.ok(pageResult);
    }
}
