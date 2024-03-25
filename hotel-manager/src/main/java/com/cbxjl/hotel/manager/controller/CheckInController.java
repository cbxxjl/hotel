package com.cbxjl.hotel.manager.controller;

import com.cbxjl.hotel.common.annotations.CheckUserType;
import com.cbxjl.hotel.common.constant.UserTypeConstants;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.common.domain.R;
import com.cbxjl.hotel.manager.core.dto.*;
import com.cbxjl.hotel.manager.service.CheckInService;
import javafx.scene.control.TextFormatter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

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

    @PostMapping("/listPage")
    @CheckUserType(value = {UserTypeConstants.RECEPTIONDESK, UserTypeConstants.ADMINISTRATOR})
    public R<PageResult<CheckInPageDTO>> listPage(@RequestBody CheckInPageParam checkInPageParam) {
        PageResult<CheckInPageDTO> pageResult = checkInService.listPage(checkInPageParam);
        return R.ok(pageResult);
    }

    /**
     * 办理入住
     *
     * @param checkInAddDTO 入住信息
     * @return 办理结果
     */
    @PostMapping("/add")
    @CheckUserType(value = {UserTypeConstants.RECEPTIONDESK, UserTypeConstants.ADMINISTRATOR})
    public R<Void> add(@RequestBody @Valid CheckInAddDTO checkInAddDTO) {
        checkInService.add(checkInAddDTO);
        return R.ok();
    }

    /**
     * 根据房间号查询入住信息
     *
     * @param roomNumber 房间号
     * @return 入住信息
     */
    @GetMapping("/getCheckInByRoom/{roomNumber}")
    @CheckUserType(value = {UserTypeConstants.RECEPTIONDESK, UserTypeConstants.ADMINISTRATOR})
    public R<CheckInDetailDTO> getCheckInByRoom(@PathVariable String roomNumber) {
        CheckInDetailDTO checkInDetailDTO = checkInService.getCheckInByRoom(roomNumber);
        return R.ok(checkInDetailDTO);
    }

    /**
     * 换房
     *
     * @param changeRoomDTO 换房DTO
     * @return 换房结果
     */
    @PostMapping("/changeRoom")
    @CheckUserType(value = {UserTypeConstants.RECEPTIONDESK, UserTypeConstants.ADMINISTRATOR})
    public R<Void> changeRoom(@Valid @RequestBody ChangeRoomDTO changeRoomDTO) {
        checkInService.changeRoom(changeRoomDTO);
        return R.ok();
    }

    /**
     * 退房
     *
     * @param roomNumber 房间号
     * @return 退房 结果
     */
    @GetMapping("/checkOut/{roomNumber}")
    @CheckUserType(value = {UserTypeConstants.RECEPTIONDESK, UserTypeConstants.ADMINISTRATOR})
    public R<Void> checkOut(@PathVariable String roomNumber) {
        checkInService.checkOut(roomNumber);
        return R.ok();
    }
}
