package com.comrade.service;

import com.comrade.model.QuestionModel;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.ChatClient;

@Service
public class OllamaChatService {

    private final ChatClient chatClient;


    public OllamaChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String askQuestion(QuestionModel questionModel){
       var response = chatClient.call(questionModel.getQuestion());
        System.out.println(response);
       return response;
    }
}
