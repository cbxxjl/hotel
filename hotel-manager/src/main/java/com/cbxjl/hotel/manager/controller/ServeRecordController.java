package com.cbxjl.hotel.manager.controller;

import com.cbxjl.hotel.common.annotations.CheckUserType;
import com.cbxjl.hotel.common.constant.UserTypeConstants;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.common.domain.R;
import com.cbxjl.hotel.manager.core.dto.ServeRecordAddDTO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordEditDTO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordPageDTO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordPageParam;
import com.cbxjl.hotel.manager.core.entity.ServeRecord;
import com.cbxjl.hotel.manager.service.ServeRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * 客房服务记录相关接口
 *
 * @author : cbxjl
 * @date : 2024/3/18 16:07
 */
@RestController
@Slf4j
@RequestMapping("/hotel/serve")
public class ServeRecordController {
    @Resource
    private ServeRecordService serveRecordService;

    /**
     * 客房服务记录 分页查询
     *
     * @param serveRecordPageParam 分页查询参数
     * @return 分页查询结果
     */
    @PostMapping("/list")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR, UserTypeConstants.RECEPTIONDESK})
    public R<PageResult<ServeRecordPageDTO>> listPage(@RequestBody ServeRecordPageParam serveRecordPageParam) {
        PageResult<ServeRecordPageDTO> pageResult = serveRecordService.listPage(serveRecordPageParam);
        return R.ok(pageResult);
    }

    /**
     * 创建客房服务
     *
     * @param serveRecordAddDTO 客房服务创建DTO
     * @return 创建结果
     */
    @PostMapping("/add")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR, UserTypeConstants.RECEPTIONDESK})
    public R<Void> addService(@Valid @RequestBody ServeRecordAddDTO serveRecordAddDTO) {
        serveRecordService.add(serveRecordAddDTO);
        return R.ok();
    }

    /**
     * 根据id获取客房服务
     *
     * @return 客房服务信息
     */
    @GetMapping("/getServe/{id}")
    public R<ServeRecordEditDTO> getServeById(@PathVariable Long id) {
        ServeRecordEditDTO serveRecordEditDTO = serveRecordService.getById(id);
        return R.ok(serveRecordEditDTO);
    }

    /**
     * 编辑客房服务
     *
     * @param serveRecordEditDTO 客房服务编辑DTO
     * @return 编辑结果
     */
    @PostMapping("/edit")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR, UserTypeConstants.RECEPTIONDESK})
    public R<Void> update(@Valid @RequestBody ServeRecordEditDTO serveRecordEditDTO) {
        serveRecordService.update(serveRecordEditDTO);
        return R.ok();
    }

    /**
     * 完成服务
     *
     * @param id 服务id
     * @return 结果
     */
    @GetMapping("/finish/{id}")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR, UserTypeConstants.RECEPTIONDESK})
    public R<Void> finishServe(@PathVariable Long id) {
        serveRecordService.finishServe(id);
        return R.ok();
    }

    /**
     * 删除还没开始的服务
     *
     * @param id 服务id
     * @return 结果
     */
    @GetMapping("/delete/{id}")
    @CheckUserType(value = {UserTypeConstants.ADMINISTRATOR, UserTypeConstants.RECEPTIONDESK})
    public R<Void> delete(@PathVariable Long id) {
        serveRecordService.delete(id);
        return R.ok();
    }

    /**
     * 派遣人员
     *
     * @param map 服务id ，人员id
     * @return 派遣结果
     */
    @PostMapping("/arrange")
    @CheckUserType(value = {UserTypeConstants.LOGISTICS, UserTypeConstants.ADMINISTRATOR})
    public R<Void> arrange(@RequestBody Map<String, Long> map) {
        serveRecordService.arrange(map);
        return R.ok();
    }
}
