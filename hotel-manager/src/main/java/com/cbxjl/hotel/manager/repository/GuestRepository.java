package com.cbxjl.hotel.manager.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbxjl.hotel.manager.core.dos.GuestDO;
import com.cbxjl.hotel.manager.core.dto.GuestPageParam;
import com.cbxjl.hotel.manager.core.entity.Guest;

import java.util.List;

/**
 * 客户仓储
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:51
 */
public interface GuestRepository {
    /**
     * 根据身份证号查询是否为新客户
     *
     * @param cardNum 身份证号码
     * @return 判断结果 F-老客户，T-新用户
     */
    Boolean checkByCardNum(String cardNum);

    /**
     * 创建新客户
     *
     * @param item 新客户
     * @return 客户id
     */
    Long add(GuestDO item);

    /**
     * 根据身份证号获取客户
     *
     * @param cardNum
     * @return 客户信息
     */
    GuestDO getByCardNum(String cardNum);

    /**
     * 客户分页查询
     *
     * @param param 分页查询参数
     * @return 分页查询结果
     */
    Page<Guest> listPage(GuestPageParam param);

    /**
     * 根据id查询客户
     *
     * @param ids 客户id
     * @return 客户列表
     */
    List<GuestDO> getGuestById(List<Long> ids);

    /**
     * 根据id查询客户
     *
     * @param id 客户id
     * @return 客户
     */
    GuestDO getGuestById(Long id);
}
