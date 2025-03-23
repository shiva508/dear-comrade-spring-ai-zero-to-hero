package com.comrade.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "image")
@Data
public class ImageConfig {
    private Integer height;
    private Integer width;
    private Integer order;
    private String format;
}
