package com.cbxjl.hotel.manager.controller;

import com.cbxjl.hotel.common.annotations.CheckUserType;
import com.cbxjl.hotel.common.constant.UserTypeConstants;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.common.domain.R;
import com.cbxjl.hotel.manager.core.dto.RoomPageDTO;
import com.cbxjl.hotel.manager.core.dto.RoomPageParam;
import com.cbxjl.hotel.manager.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 客房 相关接口
 *
 * @author : cbxjl
 * @date : 2024/3/12 11:37
 */
@Slf4j
@RestController
@RequestMapping("/hotel/room")
public class RoomController {
    @Resource
    private RoomService roomService;

    /**
     * 客房分页查询
     *
     * @param roomPageParam 分压查询参数
     * @return 查询结果
     */
    @PostMapping("/roomList")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR, UserTypeConstants.RECEPTIONDESK})
    public R<PageResult<RoomPageDTO>> roomListPage(@RequestBody RoomPageParam roomPageParam) {
        PageResult<RoomPageDTO> pageResult = roomService.listPage(roomPageParam);
        return R.ok(pageResult);
    }
}
