package com.comrade.service;

import com.comrade.model.ChatRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.model.Media;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ImageService {

    private ImageModel imageModel;

    private final ImageOptions imageOptionsBuilder;

    private final ChatClient chatClient;

    public ImageService(ImageOptions imageOptionsBuilder,
                        ChatClient chatClient,
                        Optional<ImageModel> imageModel) {
        this.imageOptionsBuilder = imageOptionsBuilder;
        this.chatClient = chatClient;
        imageModel.ifPresent(model -> this.imageModel = model);
    }

    public Resource generateImage(ChatRequestModel chatRequestModel) {

        if (imageModel == null){
            throw new RuntimeException("Not supported model");
        }
        ImagePrompt imagePrompt = new ImagePrompt(chatRequestModel.getChatMessage(),imageOptionsBuilder);
        ImageResponse imageResponse = imageModel.call(imagePrompt);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        InputStreamResource inputStreamResource = null;
        try {
            UrlResource urlResource = new UrlResource(imageUrl);
            inputStreamResource = new InputStreamResource(urlResource.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputStreamResource;
    }

    public String analyze(ChatRequestModel chatRequestModel, MultipartFile file){
        Media media = Media.builder()
                .id(file.getOriginalFilename())
                .mimeType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())))
                .data(file.getResource())
                .build();
        UserMessage userMessage = new UserMessage(chatRequestModel.getChatMessage(), media);

        return chatClient.prompt(new Prompt(userMessage))
                .call()
                .content();
    }
}
