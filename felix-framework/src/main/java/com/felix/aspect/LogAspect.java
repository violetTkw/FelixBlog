package com.felix.aspect;

import com.alibaba.fastjson.JSON;
import com.felix.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:LogAspect
 * Package:com.felix.aspect
 * Description:
 * 切面类
 *
 * @Author FelixT
 * @Create 2024/4/12 11:04
 * @Version 1.0
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.felix.annotation.SystemLog)")
    public void pt() {

    }

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            log.info("=======End=======" + System.lineSeparator());
        }
        return ret;
    }

    private void handleAfter(Object ret) {
        // 打印出参
        log.info("Response : {}", JSON.toJSONString(ret));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {

        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //获取被增强方法上的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);

        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL : {}", request.getRequestURI());
        // 打印描述信息
        log.info("BusinessName : {}", systemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method : {}",request.getMethod() );
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                ((MethodSignature)joinPoint.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP : {}",request.getRemoteHost() );
        // 打印请求入参
        log.info("Request Args : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = signature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }
}
