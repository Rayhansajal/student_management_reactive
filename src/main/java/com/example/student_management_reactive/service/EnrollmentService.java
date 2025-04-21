package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.EnrollmentDto;
import reactor.core.publisher.Flux;

public interface EnrollmentService {
    public Flux<EnrollmentDto> getAllEnrollments();
}
