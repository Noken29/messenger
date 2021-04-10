package com.example.demo.service;

import com.example.demo.entity.Message;

import java.util.ArrayList;

public interface MessageService {
    ArrayList<Message> getMessagesByChatId(Long id);
    boolean deleteMessage(Message message);
    boolean deleteMessageById(Long id);
    boolean changeMessage(Message message);
    void saveMessage(Message message);
}
