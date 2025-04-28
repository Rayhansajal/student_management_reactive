package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.entity.Result;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ResultRepository extends ReactiveCrudRepository<Result, Long> {
    Flux<Result> findByStudentId(UUID studentId);
    Mono<Result> findByStudentIdAndCourseId(UUID studentId, Long courseId);
    Flux<Result> findAllByCourseId(Long courseId);

    Flux<Result> findAllByStudentIdAndCourseId(UUID studentId, Long courseId);
}
