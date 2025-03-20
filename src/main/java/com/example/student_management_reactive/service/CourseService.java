package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.CourseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {
     Mono<CourseDto> createCourse(CourseDto courseDto);
     Flux<CourseDto> getAllCourse();
     Mono<CourseDto> getCourseById(Long course_id);
     Mono<CourseDto> updateCourse(CourseDto courseDto, Long course_id);
     Mono<Void> deleteCourse(Long course_id);






}
