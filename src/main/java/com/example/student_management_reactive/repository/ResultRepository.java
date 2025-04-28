package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.entity.Result;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ResultRepository extends ReactiveCrudRepository<Result, Long> {
    Flux<Result> findByStudentId(Long studentId);
    Mono<Result> findByStudentIdAndCourseId(Long studentId, Long courseId);
    Flux<Result> findAllByCourseId(Long courseId);

    Flux<Result> findAllByStudentIdAndCourseId(Long studentId, Long courseId);
}
