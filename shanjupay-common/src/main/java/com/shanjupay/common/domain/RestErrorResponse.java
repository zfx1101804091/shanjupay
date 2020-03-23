package com.shanjupay.common.domain;

import io.swagger.annotations.ApiModel;

/**
 * @description:
 *  TODO  自定义业务异常处理器
 * @author: zheng-fx
 * @time: 2020/3/23 0023 20:27
 */
@ApiModel(value = "RestErrorResponse", description = "错误响应参数包装")
public class RestErrorResponse {
    
    private String errCode; 
    
    private String errMessage; 
    
    public RestErrorResponse(String errCode,String errMessage) { 
        this.errCode = errCode;
        this.errMessage= errMessage; 
    }
}
