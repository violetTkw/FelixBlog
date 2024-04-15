package com.felix.handle.exception;

import com.felix.domain.ResponseResult;
import com.felix.enums.AppHttpCodeEnum;
import com.felix.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName:GlobalExceprion
 * Package:com.felix.handle.exception
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/9 9:53
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException exception){
        //打印异常信息
        log.error("出现了异常!{}",exception);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(exception.getCode(),exception.getMsg());
    }
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception exception){
        //打印异常信息
        log.error("出现了异常!{}",exception);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),exception.getMessage());
    }
}
