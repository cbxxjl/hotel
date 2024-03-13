package com.cbxjl.hotel.common.helper;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cbxjl.hotel.common.domain.LoginUser;
import lombok.NoArgsConstructor;

/**
 * 登陆助手
 *
 * @author : cbxjl
 * @date : 2024/3/7 15:26
 */
@NoArgsConstructor
public class LoginHelper {
    public static final String LOGIN_USER_KEY = "loginUser";
    public static final String USER_KEY = "userId";

    public static LoginUser getLoginUser() {
        LoginUser loginUser = (LoginUser) SaHolder.getStorage().get(LOGIN_USER_KEY);
        if (loginUser != null) {
            return loginUser;
        }
        SaSession session = StpUtil.getTokenSession();
        if (ObjectUtil.isNull(session)) {
            return null;
        }
        loginUser = (LoginUser) session.get(LOGIN_USER_KEY);
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        return loginUser;
    }
}
