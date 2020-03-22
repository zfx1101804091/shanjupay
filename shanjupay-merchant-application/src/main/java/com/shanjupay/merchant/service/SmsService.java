package com.shanjupay.merchant.service;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2020/3/22 0022 12:31
 */
public interface SmsService {

    /**
     *
      * 功能描述: 
      *     TODO 获取短信验证码
      * @Param: 
      * @Return: 
      * @Author: Administrator
      * @Date: 2020/3/22 0022 12:32
      */
    String sendMsg(String phone);
}
