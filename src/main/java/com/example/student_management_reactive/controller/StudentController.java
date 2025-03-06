package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.entity.Student;
import com.example.student_management_reactive.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<StudentDto> saveStudent(@RequestBody StudentDto studentDto){
        return studentService.createStudent(studentDto);
    }


    @GetMapping
    public Flux<StudentDto> getAllStudent(){
       return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public Mono<StudentDto> getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);}

    @PutMapping("/{id}")
    public Mono<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){
        return studentService.updateStudent(studentDto,id);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }
    @GetMapping("/find/{name}")
    public Mono<StudentDto> findStudentByName(@PathVariable String name){
        return studentService.findStudentByName(name);
    }

    @GetMapping("/search")
    public Flux<StudentDto> searchStudentsByName(@RequestParam String name){
        return studentService.searchStudentByName(name);
    }
    @GetMapping("/searchQuery")
    public Flux<StudentDto> searchByNameAndAge(@RequestParam String name, @RequestParam int age){
        return studentService.searchStudentByNameAndAge(name ,age);
    }
}
