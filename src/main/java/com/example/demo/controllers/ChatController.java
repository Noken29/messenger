package com.example.demo.controllers;

import com.example.demo.entity.Message;
import com.example.demo.service.MessageService;
import com.example.demo.websocket.Greeting;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class ChatController {
    private final MessageService messageService;
    private final HttpServletRequest httpServletRequest;

    public ChatController(MessageService messageService, HttpServletRequest httpServletRequest) {
        this.messageService = messageService;
        this.httpServletRequest = httpServletRequest;
    }

    @RequestMapping(value = "/hello/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ArrayList<Greeting> getMessages(@PathVariable("id") Long id)
    {
        ArrayList<Message> messages = messageService.getMessagesByChatId(id);
        if(!messages.isEmpty()) {
            ArrayList<Greeting> greetings = new ArrayList<>(messages.size()+1);
            for (Message message : messages) {
                Greeting greeting = new Greeting(message.getUser().getUsername(), message.getText(), id);
                greetings.add(greeting);
            }
            return greetings;
        }
        return null;
    }
}
