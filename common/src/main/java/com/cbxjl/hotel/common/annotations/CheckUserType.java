package com.cbxjl.hotel.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户职位检查--自定义注解
 *
 * @author : cbxjl
 * @date : 2024/3/8 16:14
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD})
public @interface CheckUserType {
    String[] value() default {};
}
