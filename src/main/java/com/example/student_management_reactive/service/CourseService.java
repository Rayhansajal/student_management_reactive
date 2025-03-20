package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.CourseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {
    public Mono<CourseDto> createCourse(CourseDto courseDto);
    public Flux<CourseDto> getAllCourse();
    public Mono<CourseDto> getCourseById(Long course_id);
    public Mono<CourseDto> updateCourse(CourseDto courseDto, Long course_id);
    public Mono<Void> deleteCourse(Long course_id);






}
