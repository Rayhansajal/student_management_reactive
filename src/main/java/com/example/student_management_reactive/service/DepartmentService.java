package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.DepartmentDto;
import com.example.student_management_reactive.dto.DepartmentWithCoursesDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentService {
    Mono<DepartmentDto> createDepartment(DepartmentDto departmentDto);
    Flux<DepartmentDto> getAllDepartments();
    Mono<DepartmentDto> getDepartmentById(Long id);
    Mono<DepartmentDto> updateDepartment(Long id, DepartmentDto departmentDto);
    Mono<Void> deleteDepartment(Long id);


    Mono<DepartmentWithCoursesDTO> getDepartmentWithCourses(Long departmentId);
}
