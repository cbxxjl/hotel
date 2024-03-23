package com.cbxjl.hotel.manager.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.manager.core.dos.ServeRecordDO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordPageParam;
import com.cbxjl.hotel.manager.core.entity.ServeRecord;

/**
 * 客房服务记录仓储
 *
 * @author : cbxjl
 * @date : 2024/3/18 16:08
 */
public interface ServeRecordRepository {
    /**
     * 客房服务记录 分页查询
     *
     * @param serveRecordPageParam 分页查询参数
     * @return 查询结果
     */
    Page<ServeRecord> listPage(ServeRecordPageParam serveRecordPageParam);

    /**
     * 创建客房服务
     *
     * @param serveRecordDO 客房服务
     */
    void add(ServeRecordDO serveRecordDO);

    /**
     * 根据id获取客房服务
     *
     * @param id 客房id
     * @return 客房服务信息
     */
    ServeRecordDO getById(Long id);

    /**
     * 编辑客房信息
     *
     * @param serveRecordDO 客房信息编辑DTO
     */
    void update(ServeRecordDO serveRecordDO);

    /**
     * 根据id删除客房服务
     *
     * @param id 客房服务id
     */
    void delete(Long id);
}
