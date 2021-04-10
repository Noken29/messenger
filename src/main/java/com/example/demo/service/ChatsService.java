package com.example.demo.service;

import com.example.demo.entity.Chats;

public interface ChatsService {
    Chats getChatById(Long id);
    void saveChats(Chats chats);
}
