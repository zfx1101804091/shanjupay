package com.shanjupay.merchant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MerchantApplicationBootstrapTest {
    
    @Autowired
    RestTemplate restTemplate;
    
    @Test
    public void sentMsg(){

        //短信发送接口
        String url = "http://localhost:10010/message/sendMsg";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        String body = responseEntity.getBody();
        System.out.println(body);
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
