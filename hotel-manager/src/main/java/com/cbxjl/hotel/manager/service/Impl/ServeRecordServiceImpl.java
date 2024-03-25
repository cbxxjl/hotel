package com.cbxjl.hotel.manager.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.common.exception.BusinessException;
import com.cbxjl.hotel.manager.core.dos.ServeRecordDO;
import com.cbxjl.hotel.manager.core.dos.UserDO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordAddDTO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordEditDTO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordPageDTO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordPageParam;
import com.cbxjl.hotel.manager.core.entity.ServeRecord;
import com.cbxjl.hotel.manager.repository.ServeRecordRepository;
import com.cbxjl.hotel.manager.repository.UserRepository;
import com.cbxjl.hotel.manager.service.ServeRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.context.Theme;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 客房服务记录 服务实现类
 *
 * @author : cbxjl
 * @date : 2024/3/18 16:11
 */
@Service
@Slf4j
public class ServeRecordServiceImpl implements ServeRecordService {
    @Resource
    private ServeRecordRepository serveRecordRepository;
    @Resource
    private UserRepository userRepository;

    /**
     * 客房服务记录 分页查询
     *
     * @param serveRecordPageParam 分页查询参数
     * @return 查询结果
     */
    @Override
    public PageResult<ServeRecordPageDTO> listPage(ServeRecordPageParam serveRecordPageParam) {
        Page<ServeRecord> page = serveRecordRepository.listPage(serveRecordPageParam);
        List<ServeRecord> records = page.getRecords();
        List<ServeRecordDO> doList = records.stream().map(ServeRecord::poToDo).collect(Collectors.toList());
        List<ServeRecordPageDTO> dtoList = doList.stream().map(item -> {
            ServeRecordPageDTO serveRecordPageDTO = item.doToPage();
            try {
                UserDO userDO = userRepository.getUserById(item.getUserId());
                serveRecordPageDTO.setUserName(userDO.getUserName());
            }catch (BusinessException e) {
                serveRecordPageDTO.setUserName(null);
            }
            return serveRecordPageDTO;
        }).collect(Collectors.toList());

        return new PageResult<>(dtoList, page.getTotal(), page.getSize(), page.getCurrent());
    }

    /**
     * 创建客房服务
     *
     * @param serveRecordAddDTO 客房服务DTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ServeRecordAddDTO serveRecordAddDTO) {
        ServeRecordDO serveRecordDO = serveRecordAddDTO.addToDo();
        serveRecordRepository.add(serveRecordDO);
    }

    /**
     * 根据id获取客房服务信息
     *
     * @param id
     * @return 客房服务信息
     */
    @Override
    public ServeRecordEditDTO getById(Long id) {
        ServeRecordDO serveRecordDO = serveRecordRepository.getById(id);
        return serveRecordDO.doToEdit();
    }

    /**
     * 更新客房服务
     *
     * @param serveRecordEditDTO 客房服务更新DTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ServeRecordEditDTO serveRecordEditDTO) {
        ServeRecordDO serveRecordDO = serveRecordEditDTO.editToDo();
        serveRecordRepository.update(serveRecordDO);
    }

    /**
     * 完成服务
     *
     * @param id 客房服务id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void finishServe(Long id) {
        ServeRecordDO serveRecordDO = serveRecordRepository.getById(id);
        //已完成
        serveRecordDO.setStatus(2);
        serveRecordDO.setServeEnd(new Date());
        serveRecordRepository.update(serveRecordDO);
    }

    /**
     * 删除还未开始的服务
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        ServeRecordDO serveRecordDO = serveRecordRepository.getById(id);
        if (serveRecordDO.getStatus() == 0) {
            serveRecordRepository.delete(id);
        }else {
            log.error("该服务已经开始，无法删除：{}" , id);
            throw new BusinessException("该服务已经开始，无法删除");
        }
    }

    /**
     * 派遣人员
     *
     * @param map 派遣结果
     */
    @Override
    public void arrange(Map<String, Long> map) {
        Long id = map.get("id");
        Long userId = map.get("userId");
        ServeRecordDO serveRecordDO = serveRecordRepository.getById(id);

        //设置员工id、服务状态、服务开始时间
        serveRecordDO.setUserId(userId);
        serveRecordDO.setStatus(1);
        serveRecordDO.setServeStart(new Date());

        serveRecordRepository.update(serveRecordDO);
    }
}
