package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.dto.EnrollmentDto;
import com.example.student_management_reactive.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public Mono<ResponseEntity<EnrollmentDto>> assignCoursesAndDepartment(@RequestBody EnrollmentDto dto) {
        return enrollmentService.enrollStudent(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/student/{studentId}")
    public Mono<ResponseEntity<EnrollmentDto>> getStudentEnrollment(@PathVariable UUID studentId) {
        return enrollmentService.getEnrollmentDetailsByStudentId(studentId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @GetMapping("/department/{departmentId}")
    public Flux<EnrollmentDto> getAllByDepartmentId(@PathVariable Long departmentId) {
        return enrollmentService.getAllByDepartmentId(departmentId);
    }

    @PutMapping("/{enrollmentId}")
    public Mono<EnrollmentDto> updateEnrollment(
            @PathVariable Long enrollmentId,
            @RequestBody @Validated EnrollmentDto dto) {
        return enrollmentService.updateEnrollment(enrollmentId, dto);
    }

    @DeleteMapping("/{enrollmentId}")
    public Mono<Void> deleteEnrollment(@PathVariable Long enrollmentId) {
        return enrollmentService.deleteEnrollment(enrollmentId);
    }


    @GetMapping
    public Flux<EnrollmentDto> getAllEnrollments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentService.getAllEnrollments(pageable);
    }

}
