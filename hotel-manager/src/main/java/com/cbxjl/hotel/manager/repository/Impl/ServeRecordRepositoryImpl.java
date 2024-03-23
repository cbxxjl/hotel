package com.cbxjl.hotel.manager.repository.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.utils.SnowIdUtils;
import com.cbxjl.hotel.manager.core.dos.ServeRecordDO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordPageParam;
import com.cbxjl.hotel.manager.core.entity.Room;
import com.cbxjl.hotel.manager.core.entity.ServeRecord;
import com.cbxjl.hotel.manager.mapper.ServeRecordMapper;
import com.cbxjl.hotel.manager.repository.ServeRecordRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 客房服务记录仓储实现类
 *
 * @author : cbxjl
 * @date : 2024/3/18 16:09
 */
@Repository
public class ServeRecordRepositoryImpl implements ServeRecordRepository {
    @Resource
    private ServeRecordMapper serveRecordMapper;

    /**
     * 客房服务记录 分页查询
     *
     * @param pageParam 分页查询参数
     * @return 查询结果
     */
    @Override
    public Page<ServeRecord> listPage(ServeRecordPageParam pageParam) {
        Page<ServeRecord> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        LambdaQueryWrapper<ServeRecord> queryWrapper = new LambdaQueryWrapper<>();
        //查询当前，即未完成的服务（0、1）
        if (!pageParam.getNowOrHistory()) {
            queryWrapper.in(ServeRecord::getStatus, 0, 1);
        }
        queryWrapper.eq(pageParam.getUserId() != null, ServeRecord::getUserId, pageParam.getUserId());
        queryWrapper.eq(pageParam.getServeType() != null, ServeRecord::getServeType, pageParam.getServeType());
        queryWrapper.ge(pageParam.getServeStart() != null, ServeRecord::getServeStart, pageParam.getServeStart());
        queryWrapper.le(pageParam.getServeEnd() != null, ServeRecord::getServeEnd, pageParam.getServeEnd());
        queryWrapper.orderByDesc(ServeRecord::getCreateTime);

        return serveRecordMapper.selectPage(page, queryWrapper);
    }

    /**
     * 创建客房服务
     *
     * @param serveRecordDO 客房服务
     */
    @Override
    public void add(ServeRecordDO serveRecordDO) {
        ServeRecord serveRecord = serveRecordDO.doToPo();
        serveRecord.setId(SnowIdUtils.uniqueLong());
        //默认服务未开始
        serveRecord.setStatus(0);
        serveRecordMapper.insert(serveRecord);
    }

    /**
     * 根据id获取客房服务
     *
     * @param id 客房id
     * @return 客房服务信息
     */
    @Override
    public ServeRecordDO getById(Long id) {
        ServeRecord serveRecord = serveRecordMapper.selectById(id);
        return serveRecord.poToDo();
    }

    /**
     * 编辑客房信息
     *
     * @param serveRecordDO 客房信息编辑DTO
     */
    @Override
    public void update(ServeRecordDO serveRecordDO) {
        ServeRecord serveRecord = serveRecordDO.doToPo();
        serveRecordMapper.updateById(serveRecord);
    }

    /**
     * 根据id删除客房服务
     *
     * @param id 客房服务id
     */
    @Override
    public void delete(Long id) {
        serveRecordMapper.deleteById(id);
    }
}
