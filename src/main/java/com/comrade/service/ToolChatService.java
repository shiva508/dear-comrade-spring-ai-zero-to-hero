package com.comrade.service;

import com.comrade.model.ChatRequestModel;
import com.comrade.tool.ShipperInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ToolChatService {

    private final OllamaChatModel ollamaChatModel;

    private final ChatClient chatClient;

    private final ShipperInfoService shipperInfoService;

    public String toolChatCallDateTime(ChatRequestModel chatRequestModel){

        PromptTemplate promptTemplate = new PromptTemplate("""
                {query}
                """, Map.of("query", chatRequestModel.getChatMessage()));
        Prompt prompt = new  Prompt(promptTemplate.createMessage());
        return chatClient
                .prompt(prompt)
                .tools(shipperInfoService)
                .call()
                .content();
    }

}
