package com.springkafkaproject.user_service.repositories;

import com.springkafkaproject.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
