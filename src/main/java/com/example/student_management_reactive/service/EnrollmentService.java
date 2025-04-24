package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.EnrollmentDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EnrollmentService {
    Mono<EnrollmentDto> enrollStudent(EnrollmentDto dto);
    Mono<EnrollmentDto> getEnrollmentDetailsByStudentId(Long studentId);
    Mono<EnrollmentDto> updateEnrollment(Long enrollmentId, EnrollmentDto dto);
    Mono<Void> deleteEnrollment(Long enrollmentId);
    Flux<EnrollmentDto> getAllEnrollments(Pageable pageable);
}
