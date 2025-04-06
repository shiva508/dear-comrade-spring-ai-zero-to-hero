package com.comrade.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.image.ImageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class ChatClientConfig {

    private final ChatClient chatClient;

    private ImageModel imageModel;

    public ChatClientConfig(ChatClient.Builder chatClient,
                            Optional<ImageModel> imageModel) {
        this.chatClient = chatClient.defaultAdvisors(new SimpleLoggerAdvisor()).build();
        imageModel.ifPresent(model -> this.imageModel = model);
    }

    @Bean
    public ChatClient chatClient(){
        return chatClient;
    }

}
