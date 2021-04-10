package com.example.demo.websocket;

import com.example.demo.entity.Chats;
import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.service.ChatsService;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final UserService userService;
    private final ChatsService chatsService;
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketController(UserService userService, ChatsService chatsService, MessageService messageService, SimpMessagingTemplate simpMessagingTemplate) {
        this.userService = userService;
        this.chatsService = chatsService;
        this.messageService = messageService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat")
    public void sendInstantMessage(Greeting greeting)
    {
        if(greeting.getText() != null)
        {
            Chats chat = chatsService.getChatById(greeting.getChatId());
            User user = userService.getUserByUsername(greeting.getSender());
            Message message = new Message(greeting.getText(), user, chat);
            simpMessagingTemplate.convertAndSend("/topic/chat/" + greeting.getChatId(), greeting);
            messageService.saveMessage(message);
        }
    }

}