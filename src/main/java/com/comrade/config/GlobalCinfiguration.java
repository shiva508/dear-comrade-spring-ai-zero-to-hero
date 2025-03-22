package com.comrade.config;

import com.comrade.service.MockApiService;
import com.comrade.tool.DateTimeTools;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GlobalCinfiguration {
    private final MockApiService mockApiService;

    @Bean
    public DateTimeTools dateTimeTools(){
        return new DateTimeTools(mockApiService);
    }
}
