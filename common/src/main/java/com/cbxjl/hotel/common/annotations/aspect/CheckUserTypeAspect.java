package com.cbxjl.hotel.common.annotations.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.cbxjl.hotel.common.annotations.CheckUserType;
import com.cbxjl.hotel.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 注解行为定义--用户职位检查
 *
 * @author : cbxjl
 * @date : 2024/3/8 16:17
 */
@Aspect
@Component
@Slf4j
public class CheckUserTypeAspect {
    @Before("@annotation(checkUserType)")
    public void doBefore(CheckUserType checkUserType) {
        String[] values = checkUserType.value();
        //1-无权访问，0-有权访问
        int flag = 1;
        //当前用户角色为合法角色则修改标志位
        for (String userType : values) {
            if (StpUtil.hasRole(userType)) {
                flag = 0;
                break;
            }
        }
        //遍历结束标志位仍为1,则说明无权访问
        if ((flag == 1)) {
            log.error("当前用户无权访问，{}", StpUtil.getRoleList());
            throw new BusinessException("当前用户无权访问", 401);
        }
    }
}
