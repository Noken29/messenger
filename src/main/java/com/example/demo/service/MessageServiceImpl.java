package com.example.demo.service;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MessageServiceImpl implements MessageService{
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public ArrayList<Message> getMessagesByChatId(Long id) {
        return messageRepository.findAllByChat(id);
    }

    @Override
    public boolean deleteMessage(Message message) {
        messageRepository.delete(message);
        return true;
    }

    @Override
    public boolean deleteMessageById(Long id) {
        messageRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean changeMessage(Message message) {
        return true;
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
