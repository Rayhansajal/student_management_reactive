package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.config.AuditLogService;
import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.entity.Student;
import com.example.student_management_reactive.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final AuditLogService auditLogService;

//    @PostMapping
//    public Mono<ResponseEntity<StudentDto>> createStudent(@RequestBody StudentDto studentDto) {
//        return studentService.createStudent(studentDto)
//                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, "Student already exists with the same email")))
//                .map(savedStudent -> ResponseEntity.status(HttpStatus.CREATED).body(savedStudent));
//    }
@PostMapping
public Mono<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
    return auditLogService.saveStudent(studentDto);
}



    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Flux<StudentDto> getAllStudent(){
       return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public Mono<StudentDto> getStudentById(@PathVariable UUID id){
        return studentService.getStudentById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found")));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<StudentDto> updateStudent(@PathVariable UUID id, @RequestBody StudentDto studentDto){
        return studentService.updateStudent(studentDto,id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<Void> deleteStudent(@PathVariable UUID id) {
        return studentService.deleteStudent(id);

    }

    @GetMapping("/find/{firstName}")
    public Mono<StudentDto> findByName(@PathVariable String firstName){
        return studentService.findStudentByName(firstName);
    }

    @GetMapping("/search")
    public Flux<StudentDto> searchStudentsByName(@RequestParam String firstName){
        return studentService.searchStudentByName(firstName);
    }
    @GetMapping("/searchQuery")
    public Flux<StudentDto> searchByNameAndAge(@RequestParam String firstName, @RequestParam int age){
        return studentService.searchStudentByNameAndAge(firstName ,age).delayElements(Duration.ofMillis(1000));
    }


    @PostMapping("/advancedSearch")
    public Flux<Student> searchStudents(@RequestBody Student student) {
        return studentService.advancedSearch(student);
    }


    @GetMapping("/advancedSearch2")
    public Flux<Student> searchStudents(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address
    ) {
        Student request = new Student();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setAge(age);
        request.setEmail(email);
        request.setAddress(address);

        return studentService.advancedSearch(request);
    }

}
