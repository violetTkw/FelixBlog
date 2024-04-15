package com.felix.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName:SystemLog
 * Package:com.felix.annotation
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/12 11:02
 * @Version 1.0
 */
//自定义注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog {
    String businessName();
}
