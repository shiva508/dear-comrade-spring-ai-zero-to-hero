package com.comrade.controller;

import com.comrade.model.QuestionModel;
import com.comrade.service.OllamaChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class OllamaChatController {

    private final OllamaChatService ollamaChatService;

    @PostMapping("/askQuestion")
    public Map<String,String> askQuestion(@RequestBody QuestionModel questionModel){
        log.info("OllamaChatController::askQuestion:: {}",questionModel);
        return Map.of("answer",ollamaChatService.askQuestion(questionModel));
    }

    @PostMapping("/askQuestionReactive")
    public Flux<String> askQuestionReactive(QuestionModel questionModel){
        return ollamaChatService.askQuestionReactive(questionModel);
    }
}
