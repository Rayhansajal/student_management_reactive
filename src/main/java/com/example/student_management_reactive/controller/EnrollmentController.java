package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.dto.EnrollmentDto;
import com.example.student_management_reactive.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/enrollment")
@AllArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    @GetMapping
    public Flux<EnrollmentDto> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }
}
