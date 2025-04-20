package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.entity.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DepartmentRepository extends ReactiveCrudRepository<Department,Long> {

}
