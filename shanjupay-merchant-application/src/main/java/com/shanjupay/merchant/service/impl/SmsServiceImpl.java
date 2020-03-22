package com.shanjupay.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import com.shanjupay.merchant.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2020/3/22 0022 12:49
 */
@Slf4j
@org.springframework.stereotype.Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    ConfigurableApplicationContext configurableApplicationContext;
    
    /**
     * TODO 短信验证码逻辑处理
     * @param phone
     * @return
     */
    @Override
    public String sendMsg(String phone) {
       
        //验证码过期时间时间,这里通过上下文从nacos配置中心中取（实时数据）@Value 注解有延迟
        String effectiveTime=configurableApplicationContext.getEnvironment().getProperty("sms.effectiveTime");
        //短信接口
        String url=configurableApplicationContext.getEnvironment().getProperty("sms.url")+"/sendMsg";
        
       
        //请求体
        Map<Object, Object> bodyMap = new HashMap<>();
        bodyMap.put("phone",phone);
        bodyMap.put("effectiveTime",effectiveTime);
        //请求头
        HttpHeaders headers = new HttpHeaders();
        //设置数据格式位json
        headers.setContentType(MediaType.APPLICATION_JSON);

        //封装请求参数
        HttpEntity httpEntity = new HttpEntity(bodyMap,headers);

        //发送post请求
        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        log.info("调用短信微服务发送验证码: 返回值:{}", JSON.toJSONString(exchange));
        //获取响应 
        Map responseMap = exchange.getBody();

        log.info("responseMap---{}",responseMap);
        return JSON.toJSONString(responseMap);
    }
}
