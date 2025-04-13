package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.entity.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveCrudRepository<Student ,Long> {
        Mono<Student> findByFirstName(String firstName);
        Flux<Student> findByFirstNameContainingIgnoreCase(String firstName);
        Mono<Student> findByEmail(String email);
//        Mono<Student> findById(Long id);


        //Query Method

        @Query("SELECT * FROM students WHERE  LOWER(first_name) LIKE LOWER(:firstName) AND age= :age")
         Flux<Student> findByNameAndAge(String firstName, int age);



//    Mono<Student> findByIdAndDeletedByIsNull(Long id); // Only fetch active records
//
//    Flux<Student> findByFirstNameContainingIgnoreCaseAndDeletedByIsNull(String firstName);


}
