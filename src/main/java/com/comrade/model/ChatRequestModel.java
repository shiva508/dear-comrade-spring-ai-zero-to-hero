package com.comrade.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class ChatRequestModel implements Serializable {

    private String chatMessage;
}
