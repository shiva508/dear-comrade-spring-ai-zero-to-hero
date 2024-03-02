package com.comrade.controller;

import com.comrade.model.QuestionModel;
import com.comrade.service.OllamaChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OllamaChatController {

    private final OllamaChatService ollamaChatService;

    public OllamaChatController(OllamaChatService ollamaChatService) {
        this.ollamaChatService = ollamaChatService;
    }
    @PostMapping("/askQuestion")
    public Map<String,String> askQuestion(@RequestBody QuestionModel questionModel){
        return Map.of("answer",ollamaChatService.askQuestion(questionModel));
    }
}
