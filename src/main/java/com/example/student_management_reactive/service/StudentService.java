package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.StudentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    public Mono<StudentDto> createStudent(StudentDto studentDto);
    public Flux<StudentDto> getAllStudent();
    public Mono<StudentDto> getStudentById(Long id);
    public Mono<StudentDto> updateStudent(StudentDto studentDto , Long id);
    public Mono<Void> deleteStudent(Long id);
    public Mono<StudentDto> findStudentByName(String name);
    public Flux<StudentDto> searchStudentByName(String name);
    public Flux<StudentDto>searchStudentByNameAndAge(String name,int age);
}
