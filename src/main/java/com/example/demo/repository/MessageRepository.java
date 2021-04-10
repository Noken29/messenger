package com.example.demo.repository;

import com.example.demo.entity.Message;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface MessageRepository extends CrudRepository<Message, Long> {
    @Modifying
    @Query(value = "select * from message where chat_id=:chat_id", nativeQuery = true)
    @Transactional
    ArrayList<Message> findAllByChat(@Param("chat_id") Long id);
}
