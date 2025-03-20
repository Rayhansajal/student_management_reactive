package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.entity.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CourseRepository extends ReactiveCrudRepository<Course,Long> {
}
