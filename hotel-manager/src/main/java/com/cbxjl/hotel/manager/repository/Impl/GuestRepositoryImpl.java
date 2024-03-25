package com.cbxjl.hotel.manager.repository.Impl;

import cn.dev33.satoken.secure.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.common.exception.BusinessException;
import com.cbxjl.hotel.common.utils.SnowIdUtils;
import com.cbxjl.hotel.common.utils.StringUtils;
import com.cbxjl.hotel.manager.core.dos.GuestDO;
import com.cbxjl.hotel.manager.core.dto.GuestPageParam;
import com.cbxjl.hotel.manager.core.entity.Guest;
import com.cbxjl.hotel.manager.mapper.GuestMapper;
import com.cbxjl.hotel.manager.repository.GuestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户仓储实现类
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:52
 */
@Repository
@Slf4j
public class GuestRepositoryImpl implements GuestRepository {
    @Resource
    private GuestMapper guestMapper;
    /**
     * 根据身份证号查询是否为新客户
     *
     * @param cardNum 身份证号码
     * @return 判断结果 F-老客户，T-新用户
     */
    @Override
    public Boolean checkByCardNum(String cardNum) {
        LambdaQueryWrapper<Guest> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(cardNum), Guest::getCardNum, cardNum);

        Guest guest = guestMapper.selectOne(queryWrapper);
        return guest == null;
    }

    /**
     * 创建新客户
     *
     * @param item 新客户
     */
    @Override
    public Long add(GuestDO item) {
        Guest guest = item.doToPo();
//        guest.setCardNum(BCrypt.hashpw(guest.getCardNum()));
        guest.setId(SnowIdUtils.uniqueLong());
        //新用户为1级用户
        guest.setLevel(1);
        guestMapper.insert(guest);

        return guest.getId();
    }

    /**
     * 根据身份证号获取客户
     *
     * @param cardNum
     * @return 客户信息
     */
    @Override
    public GuestDO getByCardNum(String cardNum) {
        LambdaQueryWrapper<Guest> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(cardNum), Guest::getCardNum, cardNum);

        Guest guest = guestMapper.selectOne(queryWrapper);
        if (guest == null) {
            return null;
        }
        return guest.poToDo();
    }

    /**
     * 客户分页查询
     *
     * @param param 分页查询参数
     * @return 分页查询结果
     */
    @Override
    public Page<Guest> listPage(GuestPageParam param) {
        Page<Guest> page = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<Guest> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(param.getName()), Guest::getName, param.getName());
        queryWrapper.eq(param.getLevel() != null, Guest::getLevel, param.getName());
        queryWrapper.eq(param.getSex() != null, Guest::getSex, param.getSex());
        queryWrapper.orderByDesc(Guest::getCreateTime);

        return guestMapper.selectPage(page, queryWrapper);
    }

    /**
     * 根据ids查询客户
     *
     * @param ids 客户ids
     * @return 客户列表
     */
    @Override
    public List<GuestDO> getGuestById(List<Long> ids) {
        List<Guest> guests = guestMapper.selectBatchIds(ids);

        return guests.stream().map(Guest::poToDo).collect(Collectors.toList());
    }

    /**
     * 根据id查询客户
     *
     * @param id 客户id
     * @return 客户
     */
    @Override
    public GuestDO getGuestById(Long id) {
        Guest guest = guestMapper.selectById(id);
        return guest.poToDo();
    }
}
