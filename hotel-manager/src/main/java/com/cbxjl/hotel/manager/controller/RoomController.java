package com.cbxjl.hotel.manager.controller;

import com.cbxjl.hotel.common.annotations.CheckUserType;
import com.cbxjl.hotel.common.constant.UserTypeConstants;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.common.domain.R;
import com.cbxjl.hotel.manager.core.dto.RoomAddDTO;
import com.cbxjl.hotel.manager.core.dto.RoomEditDTO;
import com.cbxjl.hotel.manager.core.dto.RoomPageDTO;
import com.cbxjl.hotel.manager.core.dto.RoomPageParam;
import com.cbxjl.hotel.manager.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    /**
     * 添加客房
     *
     * @param roomAddDTO 客房添加DTO
     * @return 添加结果
     */
    @PostMapping("/add")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR, UserTypeConstants.RECEPTIONDESK})
    public R<Void> addRoom(@Valid @RequestBody RoomAddDTO roomAddDTO) {
        roomService.add(roomAddDTO);
        return R.ok("添加成功");
    }

    /**
     * 根据房间id获取房间，回显
     *
     * @param id 房间id
     * @return 房间信息
     */
    @GetMapping("/getById/{id}")
    public R<RoomEditDTO> getById(@PathVariable Long id) {
        RoomEditDTO roomEditDTO = roomService.getById(id);
        return R.ok(roomEditDTO);
    }

    /**
     * 编辑房间信息
     *
     * @param roomEditDTO 房间编辑DTO
     * @return 编辑结果
     */
    @PostMapping("/update")
    public R<Void> update(@Valid @RequestBody RoomEditDTO roomEditDTO) {
        roomService.update(roomEditDTO);
        return R.ok("编辑成功");
    }
}
