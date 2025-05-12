package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.ResultDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ResultService {
    Mono<ResultDto> saveResult(ResultDto resultDto);
    Flux<ResultDto> getResultsByStudentId(UUID studentId);
    Flux<ResultDto> getResultsByCourseId(Long courseId);
}
