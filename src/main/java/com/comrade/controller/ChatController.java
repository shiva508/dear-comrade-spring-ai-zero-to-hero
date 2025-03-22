package com.comrade.controller;

import com.comrade.model.ChatRequestModel;
import com.comrade.service.ChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/message")
    public ResponseEntity<String> chat(@RequestBody ChatRequestModel chatRequestModel){
        Map<String, String> generate = chatService.generate(chatRequestModel);
        return new ResponseEntity<>(generate.values().stream().collect(Collectors.joining(" ")), HttpStatus.OK);
    }

    @PostMapping("/message-reactive")
    public Flux<ChatResponse> generateStream(@RequestBody ChatRequestModel chatRequestModel){
        return chatService.generateStream(chatRequestModel);
    }

    @PostMapping("/vector-storeChat")
    public List<Document> vectorStoreChat(@RequestBody ChatRequestModel chatRequestModel){
        return chatService.vectorStoreChat(chatRequestModel);
    }

    @PostMapping("/vector-store-prompt-chat")
    public String vectorStorePromptChat(@RequestBody ChatRequestModel chatRequestModel){
        return chatService.vectorStorePromptChat(chatRequestModel);
    }
}
