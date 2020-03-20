package com.shanjupay.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2020/3/20 0020 18:36
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MerchantApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(MerchantApplicationBootstrap.class,args);
    }
}
