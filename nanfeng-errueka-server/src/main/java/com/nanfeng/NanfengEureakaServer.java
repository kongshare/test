package com.nanfeng;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NanfengEureakaServer {
    public static void main(String[] args) {
        SpringApplication.run(NanfengEureakaServer.class, args);
    }
}
