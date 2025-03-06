package com.example.student_management_reactive.service;
import com.example.student_management_reactive.dto.TeacherDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherService {
    public Mono<TeacherDto> createTeacher(TeacherDto teacherDto);
    public Flux<TeacherDto> getAllTeacher();
    public Mono<TeacherDto> getStudentById(Long id);
    public Mono<TeacherDto> updateStudent(TeacherDto teacherDto , Long id);
    public Mono<Void> deleteTeacher(Long id);
}
