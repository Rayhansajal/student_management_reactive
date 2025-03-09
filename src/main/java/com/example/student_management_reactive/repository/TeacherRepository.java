package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.entity.Student;
import com.example.student_management_reactive.entity.Teacher;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherRepository extends ReactiveCrudRepository<Teacher ,Long> {

    Mono<Teacher> findByName(String name);
    Flux<Teacher> findByNameContainingIgnoringCase(String name);

    @Query("SELECT * FROM teacher WHERE LOWER(name) LIKE LOWER(:name)")
    Flux<Teacher> findTeacherNameLike(String name);
}
