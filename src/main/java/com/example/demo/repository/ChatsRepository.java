package com.example.demo.repository;

import com.example.demo.entity.Chats;
import org.springframework.data.repository.CrudRepository;

public interface ChatsRepository extends CrudRepository<Chats, Long> {
}
