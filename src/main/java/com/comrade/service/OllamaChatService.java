package com.comrade.service;

import com.comrade.model.QuestionModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.ChatClient;

@Service
@AllArgsConstructor
@Slf4j
public class OllamaChatService {

    private final ChatClient chatClient;

    public String askQuestion(QuestionModel questionModel){
       var response = chatClient.call(questionModel.getQuestion());
       log.info("OLLAMA {}",response);
       return response;
    }
}
