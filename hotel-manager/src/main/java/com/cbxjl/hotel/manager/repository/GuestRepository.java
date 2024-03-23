package com.cbxjl.hotel.manager.repository;

import com.cbxjl.hotel.manager.core.dos.GuestDO;

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
}
