package com.cbxjl.hotel.manager.service;

import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.manager.core.dto.ServeRecordAddDTO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordEditDTO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordPageDTO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordPageParam;

import java.util.Map;

/**
 * 客房服务记录 服务
 *
 * @author : cbxjl
 * @date : 2024/3/18 16:09
 */
public interface ServeRecordService {
    /**
     * 客房服务记录 分页查询
     *
     * @param serveRecordPageParam 分页查询参数
     * @return 查询结果
     */
    PageResult<ServeRecordPageDTO> listPage(ServeRecordPageParam serveRecordPageParam);

    /**
     * 创建客房服务
     *
     * @param serveRecordAddDTO 客房服务DTO
     */
    void add(ServeRecordAddDTO serveRecordAddDTO);

    /**
     * 根据id获取客房服务信息
     *
     * @param id
     * @return 客房服务信息
     */
    ServeRecordEditDTO getById(Long id);

    /**
     * 更新客房服务
     *
     * @param serveRecordEditDTO 客房服务更新DTO
     */
    void update(ServeRecordEditDTO serveRecordEditDTO);

    /**
     * 完成服务
     *
     * @param id 客房服务id
     */
    void finishServe(Long id);

    /**
     * 删除还未开始的服务
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 派遣人员
     *
     * @param map 派遣结果
     */
    void arrange(Map<String, Long> map);
}
