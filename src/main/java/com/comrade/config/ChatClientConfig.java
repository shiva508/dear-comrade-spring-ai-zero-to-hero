package com.comrade.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    private final ChatClient chatClient;

    public ChatClientConfig(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @Bean
    public ChatClient chatClient(){
        return chatClient;
    }
}
