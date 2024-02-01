package com.cbxjl.hotel.common.properties;

import com.cbxjl.hotel.common.enums.CaptchaCategory;
import com.cbxjl.hotel.common.enums.CaptchaType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 验证码配置类
 *
 * @author 谢金伦
 */
@Data
@Component
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {
    /**
     * 验证码类型
     */
    private CaptchaType type;

    /**
     * 验证码类别
     */
    private CaptchaCategory category;

    /**
     * 数字验证码位数
     */
    private Integer numberLength;

    /**
     * 字符验证码长度
     */
    private Integer charLength;
}
