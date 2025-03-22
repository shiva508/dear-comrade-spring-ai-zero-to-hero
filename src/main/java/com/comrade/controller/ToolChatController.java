package com.comrade.controller;

import com.comrade.model.ChatRequestModel;
import com.comrade.service.ToolChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tool")
@RequiredArgsConstructor
public class ToolChatController {

    private final ToolChatService toolChatService;

    @PostMapping("/chat-with-tool")
    public ResponseEntity<String> toolChatCallDateTime(@RequestBody ChatRequestModel chatRequestModel){
        return new ResponseEntity<>(toolChatService.toolChatCallDateTime(chatRequestModel), HttpStatus.OK);
    }
}
