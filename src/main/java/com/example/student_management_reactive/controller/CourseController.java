package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.dto.CourseDto;
import com.example.student_management_reactive.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CourseDto> saveCourse(@RequestBody CourseDto courseDto){
        return courseService.createCourse(courseDto);
    }


    @GetMapping
    public Flux<CourseDto> getAllCourse(){
        return courseService.getAllCourse();
    }

    @GetMapping("/{id}")
    public Mono<CourseDto> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
    @PutMapping("/{id}")
    public Mono<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto){
        return courseService.updateCourse(courseDto,id);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }
}
