package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StudentService {

     Mono<StudentDto> createStudent(StudentDto studentDto);
     Flux<StudentDto> getAllStudent();
     Mono<StudentDto> getStudentById(UUID id);
     Mono<StudentDto> updateStudent(StudentDto studentDto , UUID id);
     Mono<Void> deleteStudent(UUID id);
     Mono<StudentDto> findStudentByName(String firstName);
     Flux<StudentDto> searchStudentByName(String firstName);
     Flux<StudentDto>searchStudentByNameAndAge(String firstName,int age);



     public Flux<Student> advancedSearch(Student student);
}
