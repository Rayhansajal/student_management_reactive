package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.dto.StudentInfoDto;
import com.example.student_management_reactive.service.StudentInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/studentinfo")
@AllArgsConstructor
public class StudentInfoController {
    private final StudentInfoService enrollmentService;
    @GetMapping
    public Flux<StudentInfoDto> getAllEnrollments() {
        return enrollmentService.getAllStudent();
    }
}
