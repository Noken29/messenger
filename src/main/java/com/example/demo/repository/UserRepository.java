package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Modifying
    @Query(value = "insert into user_chats (users_id,chats_id) VALUES (:user_id,:chat_id)", nativeQuery = true)
    @Transactional
    int setChat(@Param("user_id") Long user_id, @Param("chat_id") Long chat_id);
}
