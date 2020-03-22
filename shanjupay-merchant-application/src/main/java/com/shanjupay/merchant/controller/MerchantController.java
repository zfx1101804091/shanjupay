package com.shanjupay.merchant.controller;

import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2020/3/20 0020 22:10
 */
@Api(value = "商户平台‐商户相关", tags = "商户平台‐商户相关", description = "商户平台‐商户相关")
@RestController
public class MerchantController {
    
    @Reference
    private MerchantService merchantService;
    
    @Autowired
    private SmsService smsService;

    @ApiOperation("根据id查询商户")
    @GetMapping("/merchants/{id}")
    public MerchantDTO queryMerchantById(@PathVariable("id") Long id){
        return merchantService.queryMerchantById(id);
    }

    /*
        paramType = "query" 表示 ?phone=***  即问号带参的形式传递
     */
    @ApiOperation("获取手机验证码")
    @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String", paramType = "query")
    @GetMapping("/sms")
    public String getSMSCode(@RequestParam("phone")String phone){
        return smsService.sendMsg(phone);
    }
    
    
}
