package com.shanjupay.common.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateUtils;


/**
 * @description:
 * @author: zheng-fx
 * @time: 2020/3/21 0021 19:23
 */

@Data
@NoArgsConstructor
public class CommonResult {
    
    private int status;
    private String msg;
    private Object result;
    


    public CommonResult(int status, String msg,Object result) {
        this.status = status;
        this.result = result;
        this.msg = msg;
    }
    
    public CommonResult ok(int status,String msg,Object result) {
        this.status=200;
        this.msg=msg;
        this.result = result;
        return this;
    }
    

    public CommonResult error(int status,String msg,Object result) {
        this.status=status;
        this.msg=msg;
        this.result = result;
        return this;
    }

    public CommonResult error(int status,String msg) {
        this.status=status;
        this.msg=msg;
        return this;
    }
}
