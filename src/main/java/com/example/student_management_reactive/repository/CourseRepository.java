package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.entity.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CourseRepository extends ReactiveCrudRepository<Course,Long> {
}
