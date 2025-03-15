package com.example.student_management_reactive.service.impl;

import com.example.student_management_reactive.dto.BatchDto;
import com.example.student_management_reactive.dto.CourseDto;
import com.example.student_management_reactive.entity.Batch;
import com.example.student_management_reactive.repository.BatchRepository;
import com.example.student_management_reactive.service.BatchService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@AllArgsConstructor
public class BatchServiceImpl implements BatchService {
    private final BatchRepository batchRepository;
    private final ModelMapper modelMapper;
    @Override
    public Mono<BatchDto> createBatch(BatchDto batchDto) {
        Batch batch = modelMapper.map(batchDto, Batch.class);
        return batchRepository.save(batch).map(batch1->modelMapper.map(batch1,BatchDto.class));

    }

    @Override
    public Flux<BatchDto> getAllBatch() {
        return batchRepository.findAll().map(batch -> modelMapper.map(batch,BatchDto.class));
    }

    @Override
    public Mono<BatchDto> getBatchById(Long id) {
        return batchRepository.findById(id).map(batch -> modelMapper.map(batch,BatchDto.class));
    }

    @Override
    public Mono<BatchDto> updateBatch(BatchDto batchDto, Long id) {
        return batchRepository.findById(id).flatMap(existing->{
                    existing.setName(batchDto.getName());
                    existing.setDescription(batchDto.getDescription());
                    return batchRepository.save(existing);
                })
                .map(updated-> modelMapper.map(updated, BatchDto.class));
    }

    @Override
    public Mono<Void> deleteBatch(Long id) {
        return batchRepository.deleteById(id);
    }
}
