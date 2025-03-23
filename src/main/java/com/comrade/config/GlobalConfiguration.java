package com.comrade.config;

import com.comrade.service.MockApiService;
import com.comrade.tool.ShipperInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GlobalConfiguration {
    private final MockApiService mockApiService;

    @Bean
    public ShipperInfoService shipperInfoService(){
        return new ShipperInfoService(mockApiService);
    }
}
