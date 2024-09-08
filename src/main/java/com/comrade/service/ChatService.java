package com.comrade.service;


import com.comrade.model.QuestionModel;
import reactor.core.publisher.Flux;

public interface ChatService {
    String askQuestion(QuestionModel questionModel);
    Flux<String> askQuestionReactive(QuestionModel questionModel);

}
