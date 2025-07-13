package com.comrade.controller;

import com.comrade.model.ChatRequestModel;
import com.comrade.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/craft")
    public Resource imageGenerator(@RequestBody ChatRequestModel chatRequestModel){
        return imageService.generateImage(chatRequestModel);
    }

    @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String analyze(@RequestPart("chatMessage") String chatMessage, @RequestPart("file")MultipartFile file){
        ChatRequestModel chatRequestModel = new ChatRequestModel();
        chatRequestModel.setChatMessage(chatMessage);
        return imageService.analyze(chatRequestModel,file);
    }
}
