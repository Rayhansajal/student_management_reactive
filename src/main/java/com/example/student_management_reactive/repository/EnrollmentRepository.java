package com.example.student_management_reactive.repository;

import com.example.student_management_reactive.entity.Enrollment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EnrollmentRepository extends ReactiveCrudRepository<Enrollment,Long> {

    Flux<Enrollment> findAllByStudentId(Long studentId);

    Flux<Enrollment> findAllByCourseId(Long courseId);

    Flux<Enrollment> findAllByDepartmentId(Long departmentId);

    Flux<Enrollment> findAllByStudentIdAndDepartmentId(Long studentId, Long departmentId);


    Mono<Boolean> existsByStudentIdAndCourseIdAndDepartmentId(Long studentId, Long courseId, Long departmentId);

    @Query("DELETE FROM enrollments WHERE student_id = :studentId")
    Mono<Void> deleteByStudentId(Long studentId);


    @Query("SELECT * FROM enrollments ORDER BY enrollment_date DESC LIMIT :limit OFFSET :offset")
    Flux<Enrollment> findAllPaged(@Param("limit") int limit, @Param("offset") int offset);
}
