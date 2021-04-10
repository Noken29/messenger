package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    User getUserById(Long id);
    User getUserByUsername(String username);
    boolean saveUser(User user);
    void setChat(Long userId, Long chatId);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

}
