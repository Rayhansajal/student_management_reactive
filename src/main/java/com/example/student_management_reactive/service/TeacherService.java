package com.example.student_management_reactive.service;
import com.example.student_management_reactive.dto.TeacherDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherService {
     Mono<TeacherDto> createTeacher(TeacherDto teacherDto);
     Flux<TeacherDto> getAllTeacher();
     Mono<TeacherDto> getStudentById(Long id);
     Mono<TeacherDto> updateStudent(TeacherDto teacherDto , Long id);
     Mono<Void> deleteTeacher(Long id);
     Mono<TeacherDto> findTeacherByName(String name);
     Flux<TeacherDto> searchTeacherByName(String name);
     Flux<TeacherDto> searchNameLike(String name);
}
