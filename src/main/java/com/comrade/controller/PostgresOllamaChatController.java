package com.comrade.controller;

import com.comrade.model.QuestionModel;
import com.comrade.service.PostgresOllamaChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ai/postgresllm")
public class PostgresOllamaChatController {

    private final PostgresOllamaChatService postgresOllamaChatService;

    @PostMapping("/askQuestion")
    public Map<String,String> askQuestion(@RequestBody QuestionModel questionModel){
        return Map.of("answer",postgresOllamaChatService.askQuestion(questionModel));
    }

}
