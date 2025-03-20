package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.BatchDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BatchService {
    public Mono<BatchDto> createBatch(BatchDto batchDto);
    public Flux<BatchDto> getAllBatch();
    public Mono<BatchDto> getBatchById(Long batch_id);
    public Mono<BatchDto> updateBatch(BatchDto batchDto , Long batch_id);
    public Mono<Void> deleteBatch(Long batch_id);

}
