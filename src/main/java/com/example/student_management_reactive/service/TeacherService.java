package com.example.student_management_reactive.service;
import com.example.student_management_reactive.dto.TeacherDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TeacherService {
     Mono<TeacherDto> createTeacher(TeacherDto teacherDto);
     Flux<TeacherDto> getAllTeacher();
     Mono<TeacherDto> getStudentById(UUID id);
     Mono<TeacherDto> updateStudent(TeacherDto teacherDto , UUID id);
     Mono<Void> deleteTeacher(UUID id);
     Mono<TeacherDto> findTeacherByName(String name);
     Flux<TeacherDto> searchTeacherByName(String name);
     Flux<TeacherDto> searchNameLike(String name);
}
