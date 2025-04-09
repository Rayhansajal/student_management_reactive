package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User,Long> {
    Mono<User> findByUsername(String username);
}
