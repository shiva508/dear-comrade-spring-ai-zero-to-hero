package com.comrade.model;

import java.io.Serializable;

public class QuestionModel implements Serializable {
    private String question;


    public QuestionModel() {
    }

    public QuestionModel(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
