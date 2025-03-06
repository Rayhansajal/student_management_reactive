package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.entity.Student;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveCrudRepository<Student ,Long> {
        Mono<StudentDto> findByName(String name);
        Flux<StudentDto> findByNameContainingIgnoreCase(String name);

        //Query Method

        @Query("SELECT * FROM students WHERE name LIKE :name AND age= :age")
         Flux<StudentDto> findByNameAndAge(String name, int age);
}
