package com.cbxjl.hotel.manager.repository.Impl;

import com.cbxjl.hotel.common.utils.SnowIdUtils;
import com.cbxjl.hotel.manager.core.dos.CheckInRecordDO;
import com.cbxjl.hotel.manager.core.entity.CheckInRecord;
import com.cbxjl.hotel.manager.mapper.CheckInRecordMapper;
import com.cbxjl.hotel.manager.repository.CheckInRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 入住管理仓储实现类
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:27
 */
@Repository
public class CheckInRepositoryImpl implements CheckInRepository {
    @Resource
    private CheckInRecordMapper checkInRecordMapper;

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
}
