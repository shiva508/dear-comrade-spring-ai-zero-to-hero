package com.comrade.config;

import com.comrade.service.MockApiService;
import com.comrade.tool.ShipperInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GlobalConfiguration {
    private final MockApiService mockApiService;
    private final ImageConfig imageConfig;

    @Bean
    public ShipperInfoService shipperInfoService(){
        return new ShipperInfoService(mockApiService);
    }

    @Bean
    public ImageOptions imageOptionsBuilder(){
        return ImageOptionsBuilder.builder()
                .width(imageConfig.getWidth())
                .height(imageConfig.getHeight())
                .N(imageConfig.getOrder())
                .responseFormat(imageConfig.getFormat())
                .build();
    }
}
