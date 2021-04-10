package com.example.demo.service;

import com.example.demo.entity.Chats;
import com.example.demo.repository.ChatsRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatsServiceImpl implements ChatsService{
    private final ChatsRepository chatsRepository;

    public ChatsServiceImpl(ChatsRepository chatsRepository) {
        this.chatsRepository = chatsRepository;
    }

    @Override
    public Chats getChatById(Long id) {
        if(chatsRepository.findById(id).isPresent())
            return chatsRepository.findById(id).get();
        return null;
    }

    @Override
    public void saveChats(Chats chats) {
        chatsRepository.save(chats);
    }
}
