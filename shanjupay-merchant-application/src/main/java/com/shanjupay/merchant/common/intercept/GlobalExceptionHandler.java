package com.shanjupay.merchant.common.intercept;

import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import com.shanjupay.common.domain.ErrorCode;
import com.shanjupay.common.domain.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * TODO 定义全局异常处理器 
 *  全局异常处理器使用ControllerAdvice注解实现，ControllerAdvice
 *  是SpringMVC3.2提供的注解，用 ControllerAdvice可以方便实现对Controller面向切面编程，具体用法如下：
 * 1、ControllerAdvice和ExceptionHandler注解实现全局异常处理 
 * 2、ControllerAdvice和ModelAttribute注解实现全局数据绑定 
 * 3、ControllerAdvice生InitBinder注解实现全局数据预处理
 * 
 * @author: zheng-fx
 * @time: 2020/3/23 0023 20:29
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    //捕获异常后处理方法
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//500异常
    public RestErrorResponse processExcetion(HttpServletRequest request, 
                                             HttpServletResponse response,
                                             Exception e){
        //如果是自定义异常则直接取出异常信息
        if(e instanceof BusinessException){
            log.error(e.getMessage(),e);
            BusinessException businessException = (BusinessException)e;
            ErrorCode errorCode = businessException.getErrorCode();

            return new RestErrorResponse(String.valueOf(errorCode.getCode()),errorCode.getDesc());
        }
        log.error("系统异常：",e); 
        return new RestErrorResponse(String.valueOf(CommonErrorCode.UNKOWN.getCode()),CommonErrorCode.UNKOWN.getDesc());
    }
}
