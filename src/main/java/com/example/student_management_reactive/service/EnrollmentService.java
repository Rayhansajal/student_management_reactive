package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.EnrollmentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EnrollmentService {
    Mono<EnrollmentDto> enrollStudent(EnrollmentDto dto);
    Mono<EnrollmentDto> getEnrollmentDetailsByStudentId(Long studentId);
    public Mono<EnrollmentDto> updateEnrollment(Long enrollmentId, EnrollmentDto dto);
    public Mono<Void> deleteEnrollment(Long enrollmentId);
}
