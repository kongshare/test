package com.nanfeng.congfiguration.GlobalConfigutaion;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GrobalConfiguration {

//    @Bean
//    public CorsFilter corsFilter(){
//    //1.添加cors配置信息
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://manage.leyou.com");
//        //设置允许发送携带cookie
//        configuration.setAllowCredentials(true);
//        configuration.addAllowedMethod("HEAD");
//        configuration.addAllowedMethod("GET");
//        configuration.addAllowedMethod("PUT");
//        configuration.addAllowedMethod("POST");
//        configuration.addAllowedMethod("OPTIONS");
//        configuration.addAllowedMethod("DELETE");
//        configuration.addAllowedHeader("*");
//
//            //设置有效器
//        configuration.setMaxAge(360000L);
//        //2.添加映射路径
//        UrlBasedCorsConfigurationSource urlconfiguration = new UrlBasedCorsConfigurationSource();
//        urlconfiguration.registerCorsConfiguration("/**",configuration);
//        //3.返回新的corsfilters
//        return new CorsFilter(urlconfiguration);
//    }

    @Bean
    public CorsFilter corsFilter(ConfiguratinProerties prop){
        //1.添加cors配置信息
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(prop.getAllowOrigins());
        //设置允许发送携带cookie
        configuration.setAllowCredentials(prop.getAllowCredentials());

        configuration.setAllowedMethods(prop.getAllowedMethods());
        configuration.setAllowedHeaders(prop.getAllowedHeaders());
        configuration.setMaxAge(prop.getMaxAge());
        //2.添加映射路径
        UrlBasedCorsConfigurationSource urlconfiguration = new UrlBasedCorsConfigurationSource();
        urlconfiguration.registerCorsConfiguration(prop.getFilePath(),configuration);
        //3.返回新的corsfilters
        return new CorsFilter(urlconfiguration);
    }

}
