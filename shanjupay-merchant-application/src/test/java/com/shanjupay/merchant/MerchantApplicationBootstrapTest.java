package com.shanjupay.merchant;

import com.alibaba.fastjson.JSON;
import feign.HeaderMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MerchantApplicationBootstrapTest {
    
    @Autowired
    RestTemplate restTemplate;
    
    @Test
    public void sentMsg(){

        //GET请求  短信发送接口
        String url = "http://localhost:10010/message/sendMsg";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        String body = responseEntity.getBody();
        System.out.println(body);
    }
    
    @Test
    public void sentMsgToGetToPost(){
        //手机号
        String phone = "18239983359";
        //验证码过期时间5分账
        String effectiveTime = "300";
        //短信接口
        String url = "http://localhost:10010/message/sendMsg";
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
    }
    

    @Test
    public void CrawlBaidu(){

        //抓取百度页面
        String url = "https://www.baidu.com";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        String body = responseEntity.getBody();
        System.out.println(body);
    }
}
