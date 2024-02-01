package com.cbxjl.hotel.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.util.IdUtil;
import com.cbxjl.hotel.common.constant.CacheConstants;
import com.cbxjl.hotel.common.constant.Constants;
import com.cbxjl.hotel.common.domain.R;
import com.cbxjl.hotel.common.properties.CaptchaProperties;
import com.cbxjl.hotel.common.utils.redis.RedisUtils;
import com.cbxjl.hotel.common.utils.spring.SpringUtils;
import com.cbxjl.hotel.system.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码
 *
 * @author : cbxjl
 * @date : 2024/1/30 10:31
 */
@SaIgnore
@Slf4j
@RestController
@RequestMapping("/captchaImage")
public class CaptchaController {
    @Resource
    private CaptchaService captchaService;
    /**
     * 生成验证码
     */
    @GetMapping("/get")
    public R<Map<String, Object>> getCode() {
        Map<String, Object> ajax = new HashMap<>();
        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
        //生成验证码
        AbstractCaptcha captcha = captchaService.buildCaptcha(verifyKey);
        ajax.put("uuid", uuid);
        ajax.put("img", captcha.getImageBase64());
        return R.ok(ajax);
    }
}
