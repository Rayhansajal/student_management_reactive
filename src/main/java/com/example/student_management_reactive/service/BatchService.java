package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.BatchDto;
import com.example.student_management_reactive.dto.CourseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BatchService {
    public Mono<BatchDto> createBatch(BatchDto batchDto);
    public Flux<BatchDto> getAllBatch();
    public Mono<BatchDto> getBatchById(Long id);
    public Mono<BatchDto> updateBatch(BatchDto batchDto , Long id);
    public Mono<Void> deleteBatch(Long id);
}
