package com.comrade.service;

import com.comrade.model.QuestionModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.model.ChatModel;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
@Slf4j
public class OllamaChatService {

    private final ChatModel ChatModel;
    private final StreamingChatModel streamingChatModel;

    public String askQuestion(QuestionModel questionModel){
        log.info("OllamaChatService::askQuestion::{}",questionModel);
       var response = ChatModel.call(questionModel.getQuestion());
       log.info("OllamaChatService:askQuestion::response:: {}",response);
       return response;
    }

    public Flux<String> askQuestionReactive(QuestionModel questionModel){
        return streamingChatModel.stream(questionModel.getQuestion());
    }
}
