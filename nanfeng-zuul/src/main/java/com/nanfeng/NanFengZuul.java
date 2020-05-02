package com.nanfeng;


import com.nanfeng.congfiguration.GlobalConfigutaion.ConfiguratinProerties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableConfigurationProperties(ConfiguratinProerties.class)
public class NanFengZuul {
    public static void main(String[] args) {
        SpringApplication.run(NanFengZuul.class, args);
    }
}
