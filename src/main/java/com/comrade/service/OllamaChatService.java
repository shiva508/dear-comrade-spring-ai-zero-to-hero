package com.comrade.service;

import com.comrade.model.QuestionModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OllamaChatService {

    private final OllamaChatModel ollamaChatModel;

    public String askQuestion(QuestionModel questionModel){
       var response = ollamaChatModel.call(questionModel.getQuestion());
       log.info("OLLAMA {}",response);
       return response;
    }
}
