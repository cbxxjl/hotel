package com.cbxjl.hotel.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.cbxjl.hotel.common.constant.Constants;
import com.cbxjl.hotel.common.core.domain.dto.UserInfoDTO;
import com.cbxjl.hotel.common.domain.R;
import com.cbxjl.hotel.common.core.domain.dto.LoginBody;
import com.cbxjl.hotel.system.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 *
 * @author : cbxjl
 * @date : 2024/1/29 11:26
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginservice;

    //测试登录admin admin123
    @PostMapping("/passwordLogin")
    public R<Map<String, Object>> doLogin(@RequestBody LoginBody loginBody) {
        Map<String, Object> ajax = new HashMap<>();
        String token = loginservice.passwordLogin(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);

        return R.ok(ajax);
    }

    /**
     * 获取当前登录用户的信息
     *
     * @return 用户信息
     */
    @GetMapping("/getLoginEmployee")
    public R<UserInfoDTO> getLoginEmployee() {
        Long userId = StpUtil.getLoginIdAsLong();
        UserInfoDTO userInfoDTO = loginservice.getLoginUser(userId);
        return R.ok(userInfoDTO);
    }
}
