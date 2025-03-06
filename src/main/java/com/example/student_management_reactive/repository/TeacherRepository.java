package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.entity.Teacher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TeacherRepository extends ReactiveCrudRepository<Teacher ,Long> {
}
