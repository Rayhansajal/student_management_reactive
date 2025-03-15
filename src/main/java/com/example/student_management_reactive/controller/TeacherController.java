package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.dto.TeacherDto;
import com.example.student_management_reactive.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TeacherDto> saveTeacher(@RequestBody TeacherDto teacherDto){
        return teacherService.createTeacher(teacherDto);
    }


    @GetMapping
    public Flux<TeacherDto> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @GetMapping("/{id}")
    public Mono<TeacherDto> getTeacherById(@PathVariable Long id){
        return teacherService.getStudentById(id);}

    @PutMapping("/{id}")
    public Mono<TeacherDto> updateTeacher(@PathVariable Long id, @RequestBody TeacherDto teacherDto ){
        return teacherService.updateStudent(teacherDto,id);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteTeacher(@PathVariable Long id) {
        return teacherService.deleteTeacher(id);
    }

    @GetMapping("/find/{name}")
    public Mono<TeacherDto> findTeacherByName(@PathVariable String name){
        return teacherService.findTeacherByName(name);
    }
    @GetMapping("/search")
    public Flux<TeacherDto> searchTeacherByName(@RequestParam String name){
        return teacherService.searchTeacherByName(name);
    }

    @GetMapping("/searchQuery")
    public Flux<TeacherDto> searchByNameLike(@RequestParam String name) {
        return teacherService.searchNameLike(name).delayElements(Duration.ofMillis(2000));
    }
}
