package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.BatchDto;
import com.example.student_management_reactive.dto.CourseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {
    public Mono<CourseDto> createCourse(CourseDto courseDto);
    public Flux<CourseDto> getAllCourse();
    public Mono<CourseDto> getCourseById(Long id);
    public Mono<CourseDto> updateCourse(CourseDto courseDto, Long id);
    public Mono<Void> deleteCourse(Long id);
}
