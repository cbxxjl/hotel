package com.cbxjl.hotel.manager.repository.Impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.exception.BusinessException;
import com.cbxjl.hotel.common.utils.SnowIdUtils;
import com.cbxjl.hotel.common.utils.StringUtils;
import com.cbxjl.hotel.manager.core.dos.CheckInRecordDO;
import com.cbxjl.hotel.manager.core.dos.GuestDO;
import com.cbxjl.hotel.manager.core.dos.RoomDO;
import com.cbxjl.hotel.manager.core.dto.CheckInPageParam;
import com.cbxjl.hotel.manager.core.entity.CheckInRecord;
import com.cbxjl.hotel.manager.mapper.CheckInRecordMapper;
import com.cbxjl.hotel.manager.mapper.RoomMapper;
import com.cbxjl.hotel.manager.repository.CheckInRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 入住管理仓储实现类
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:27
 */
@Repository
@Slf4j
public class CheckInRepositoryImpl implements CheckInRepository {
    @Resource
    private CheckInRecordMapper checkInRecordMapper;
    @Resource
    public RoomMapper roomMapper;

    /**
     * 办理入住
     *
     * @param checkInRecordDO 入住信息
     */
    @Override
    public void add(CheckInRecordDO checkInRecordDO) {
        CheckInRecord checkInRecord = checkInRecordDO.doToPo();
        checkInRecord.setId(SnowIdUtils.uniqueLong());
        checkInRecordMapper.insert(checkInRecord);
    }

    /**
     * 根据客户id查询房间号
     *
     * @param id 客户id
     * @return 如果没有则返回未入住
     */
    @Override
    public String getRoomByGuestId(Long id) {
        LambdaQueryWrapper<CheckInRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(CheckInRecord::getGuestIds, id);
        //退房时间为空，说明还未退房
        queryWrapper.isNull(CheckInRecord::getCheckOutRealTime);
        CheckInRecord checkInRecord = checkInRecordMapper.selectOne(queryWrapper);
        //为空说明当前客户没有入住酒店
        if (checkInRecord == null) {
            return "未入住";
        }
        return checkInRecord.getRoomNumber();
    }

    /**
     * 根据用户id获取最近一次的入住时间
     *
     * @param id 客户id
     * @return 最近一次的入住时间
     */
    @Override
    public Date getRecentCheckInTimeByGuestId(Long id) {
        LambdaQueryWrapper<CheckInRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(CheckInRecord::getGuestIds, id);
        queryWrapper.orderByDesc(CheckInRecord::getCheckInTime);
        // 限制查询结果数量为1
        queryWrapper.last("LIMIT 1");

        List<CheckInRecord> checkInRecords = checkInRecordMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(checkInRecords)) {
            return checkInRecords.get(0).getCheckInTime();
        }else {
            log.error("未找到客户数据：{}", id);
            throw new BusinessException("未找到客户数据: " + id);
        }
    }

    /**
     * 入住管理 分页查询
     *
     * @param pageParam 分页查询参数
     * @return 分页结果
     */
    @Override
    public Page<CheckInRecordDO> listPage(CheckInPageParam pageParam) {
        Page<CheckInRecordDO> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        return checkInRecordMapper.listPage(page, pageParam);
    }

    /**
     * 根据房间号查询入住信息 (即最近的一次入住信息)
     *
     * @param roomNumber 房间号
     * @return 入住信息
     */
    @Override
    public CheckInRecordDO getCheckInByRoom(String roomNumber) {
        LambdaQueryWrapper<CheckInRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(roomNumber), CheckInRecord::getRoomNumber, roomNumber);
        queryWrapper.orderByDesc(CheckInRecord::getCheckInTime);
        // 限制查询结果数量为1
        queryWrapper.last("LIMIT 1");
        List<CheckInRecord> checkInRecords = checkInRecordMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(checkInRecords)) {
            return checkInRecords.get(0).poToDo();
        }else {
            log.error("未找到入住信息：{}", roomNumber);
            throw new BusinessException("系统繁忙，请联系管理员");
        }
    }

    /**
     * 通过id获取入住记录
     *
     * @param checkInId 入住记录id
     * @return 入住记录
     */
    @Override
    public CheckInRecordDO getCheckInById(Long checkInId) {
        CheckInRecord checkInRecord = checkInRecordMapper.selectById(checkInId);
        return checkInRecord.poToDo();
    }

    /**
     * 更新入住信息
     *
     * @param checkInDO 入住信息
     */
    @Override
    public void update(CheckInRecordDO checkInDO) {
        CheckInRecord checkInRecord = checkInDO.doToPo();
        checkInRecordMapper.updateById(checkInRecord);
    }
}
