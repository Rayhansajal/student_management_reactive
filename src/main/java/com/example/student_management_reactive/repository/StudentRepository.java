package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.entity.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveCrudRepository<Student ,Long> {
        Mono<Student> findByName(String name);
        Flux<Student> findByNameContainingIgnoreCase(String name);

        //Query Method

        @Query("SELECT * FROM students WHERE  LOWER(name) LIKE LOWER(:name) AND age= :age")
         Flux<Student> findByNameAndAge(String name, int age);
}
