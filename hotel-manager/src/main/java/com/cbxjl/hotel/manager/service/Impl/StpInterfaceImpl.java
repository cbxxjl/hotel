package com.cbxjl.hotel.manager.service.Impl;

import cn.dev33.satoken.stp.StpInterface;
import com.cbxjl.hotel.common.enums.UserType;
import com.cbxjl.hotel.manager.core.dos.UserDO;
import com.cbxjl.hotel.manager.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private UserRepository userRepository;
    /**
     * 返回指定账号id所拥有的权限码集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    /**
     * 返回指定账号id所拥有的角色标识集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> uerTypeList = new ArrayList<>();
        UserDO userDO = userRepository.getUserById(Long.valueOf(loginId.toString()));
        uerTypeList.add(userDO.getUserType().toString());

        return uerTypeList;
    }
}
