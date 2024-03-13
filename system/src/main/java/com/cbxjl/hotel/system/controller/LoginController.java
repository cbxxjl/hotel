package com.cbxjl.hotel.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.cbxjl.hotel.common.constant.Constants;
import com.cbxjl.hotel.common.domain.LoginUser;
import com.cbxjl.hotel.common.domain.R;
import com.cbxjl.hotel.common.domain.LoginBody;
import com.cbxjl.hotel.system.service.LoginService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class LoginController {
    @Resource
    private LoginService loginservice;


    //测试登录admin admin123
    @PostMapping("/passwordLogin")
    public R<Map<String, Object>> doLogin(@RequestBody LoginBody loginBody) {
        Map<String, Object> ajax = new HashMap<>();
        String token = loginservice.passwordLogin(loginBody.getAccount(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        log.info("登陆成功");
        ajax.put(Constants.TOKEN, token);
        return R.ok(ajax);
    }

    /**
     * 获取当前登录用户的信息
     *
     * @return 用户信息
     */
    @GetMapping("/getLoginEmployee")
    public R<LoginUser> getLoginEmployee() {
        Long userId = StpUtil.getLoginIdAsLong();
        LoginUser loginUser = loginservice.getLoginUser(userId);
        log.info("当前登录用户信息：{}", loginUser);
        return R.ok(loginUser);
    }

    /**
     * 退出登录
     *
     * @param id 用户id
     * @return 推出结果
     */
    @GetMapping("/logout/{id}")
    private R<Void> logout(@PathVariable Long id) {
        StpUtil.logout();
        log.info("退出登录");
        return R.ok();
    }
}
