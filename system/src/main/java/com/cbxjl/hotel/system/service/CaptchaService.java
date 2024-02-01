package com.cbxjl.hotel.system.service;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.cbxjl.hotel.common.constant.Constants;
import com.cbxjl.hotel.common.enums.CaptchaType;
import com.cbxjl.hotel.common.exception.BusinessException;
import com.cbxjl.hotel.common.properties.CaptchaProperties;
import com.cbxjl.hotel.common.utils.redis.RedisUtils;
import com.cbxjl.hotel.common.utils.spring.SpringUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * 验证码服务
 *
 * @author : cbxjl
 * @date : 2024/1/30 10:49
 */
@Service
@Slf4j
public class CaptchaService {
    @Resource
    private CaptchaProperties captchaProperties;

    /**
     * 生成验证码
     *
     * @param key  验证码 键
     */
    public AbstractCaptcha buildCaptcha(String key) {
        // 验证码类型
        CaptchaType captchaType = captchaProperties.getType();
        boolean isMath = CaptchaType.MATH == captchaType;
        Integer length = isMath ? captchaProperties.getNumberLength() : captchaProperties.getCharLength();
        CodeGenerator codeGenerator = ReflectUtil.newInstance(captchaType.getClazz(), length);
        AbstractCaptcha captcha = SpringUtils.getBean(captchaProperties.getCategory().getClazz());
        captcha.setGenerator(codeGenerator);
        captcha.createCode();
        String code = captcha.getCode();
        if (isMath) {
            ExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression(StringUtils.remove(code, "="));
            code = exp.getValue(String.class);
        }
        RedisUtils.setCacheObject(key, code, Duration.ofMinutes(Constants.CAPTCHA_EXPIRATION));

        return captcha;
    }
}
