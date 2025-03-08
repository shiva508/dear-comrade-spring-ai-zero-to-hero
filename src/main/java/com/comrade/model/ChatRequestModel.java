package com.comrade.model;


import java.io.Serializable;

public class ChatRequestModel implements Serializable {

    private String chatMessage;
    public ChatRequestModel() {
    }

    public ChatRequestModel(String chatMessage) {
        this.chatMessage = chatMessage;
    }



    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }
}
