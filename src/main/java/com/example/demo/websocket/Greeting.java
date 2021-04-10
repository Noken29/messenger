package com.example.demo.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("text")
    private String text;

    @JsonProperty("chatId")
    private Long chatId;

    public Greeting() {
    }

    public Greeting(String sender, String text, Long chatId) {
        this.sender = sender;
        this.text = text;
        this.chatId = chatId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}
