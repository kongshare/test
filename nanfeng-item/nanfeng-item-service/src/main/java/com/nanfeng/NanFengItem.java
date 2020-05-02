package com.nanfeng;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.nanfeng.mapper")
public class NanFengItem {
    public static void main(String[] args) {
        SpringApplication.run(NanFengItem.class, args);
    }
}