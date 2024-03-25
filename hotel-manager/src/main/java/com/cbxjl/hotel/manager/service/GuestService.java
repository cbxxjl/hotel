package com.cbxjl.hotel.manager.service;

import com.cbxjl.hotel.common.domain.PageResult;
import com.cbxjl.hotel.manager.core.dto.GuestPageDTO;
import com.cbxjl.hotel.manager.core.dto.GuestPageParam;

/**
 * 客户  服务
 *
 * @author : cbxjl
 * @date : 2024/3/23 14:19
 */
public interface GuestService {
    /**
     * 客户分页查询
     *
     * @param param 分页查询参数
     * @return 分页查询结果
     */
    PageResult<GuestPageDTO> listPage(GuestPageParam param);
}
