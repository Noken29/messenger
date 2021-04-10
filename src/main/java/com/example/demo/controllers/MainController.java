package com.example.demo.controllers;

import com.example.demo.entity.Chats;
import com.example.demo.entity.User;
import com.example.demo.service.ChatsService;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private final UserService userService;
    private final ChatsService chatsService;
    private final MessageService messageService;
    private final HttpServletRequest httpServletRequest;

    public MainController(UserService userService, ChatsService chatsService, MessageService messageService, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.chatsService = chatsService;
        this.messageService = messageService;
        this.httpServletRequest = httpServletRequest;
    }

    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("User",new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@ModelAttribute("User") User user)
    {
        if(!userService.saveUser(user))
            return "registration";
        return "redirect:/login";
    }
    @GetMapping("/hello")
    public String hello(Model model)
    {
        model.addAttribute("NewChat",new Chats());
        User user = userService.getUserByUsername(httpServletRequest.getRemoteUser());
        if(!user.getChats().isEmpty())
            model.addAttribute("chats", user.getChats());
        return "hello";
    }
    @PostMapping("/hello")
    public String createChat(@ModelAttribute("NewChat") Chats chats)
    {
        if(chats.getName()!=null) {
            chatsService.saveChats(chats);
            userService.setChat(userService.getUserByUsername(httpServletRequest.getRemoteUser()).getId(),chats.getId());
        }
        return "redirect:/hello";
    }

    @GetMapping("/hello/{id}")
    public String chat(@PathVariable("id") Long id, Model model)
    {
        User user = userService.getUserByUsername(httpServletRequest.getRemoteUser());
        Chats chat = chatsService.getChatById(id);
        if(user.getChats().contains(chat))
        {
            //ArrayList<Message> messages = messageService.getMessagesByChatId(id);
            //if(!messages.isEmpty())
              //  model.addAttribute("messages", messages);
            model.addAttribute("chatId", id);
            model.addAttribute("user", user.getUsername());
            model.addAttribute("invitedUser",new User());
            return "chat";
        }
        return "redirect:/hello";
    }
    @PostMapping("/hello/{id}")
    public String inviteUser(@PathVariable("id") Long id, @ModelAttribute("invitedUser") User invitedUser)
    {
        User user = userService.getUserByUsername(invitedUser.getUsername());
        if(user != null && !chatsService.getChatById(id).getUsers().contains(user)) {
            userService.setChat(user.getId(), id);
        }
        return "redirect:/hello/"+id;
    }
}
