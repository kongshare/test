package com.nanfeng.congfiguration.GlobalConfigutaion;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "nanfeng.cors")
public class ConfiguratinProerties {
    public List<String> getAllowOrigins() {
        return allowOrigins;
    }

    public Boolean getAllowCredentials() {
        return allowCredentials;
    }

    public List<String> getAllowedHeaders() {
        return allowedHeaders;
    }

    public List<String> getAllowedMethods() {
        return allowedMethods;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public String getFilePath() {
        return filePath;
    }

    private List<String > allowOrigins;
    private Boolean allowCredentials ;
    private List<String>allowedHeaders;
    private List<String> allowedMethods;
    private Long maxAge;
    private String filePath;


}
