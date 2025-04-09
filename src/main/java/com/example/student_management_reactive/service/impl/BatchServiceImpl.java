package com.example.student_management_reactive.service.impl;
import com.example.student_management_reactive.dto.BatchDto;
import com.example.student_management_reactive.entity.Batch;
import com.example.student_management_reactive.repository.BatchRepository;
import com.example.student_management_reactive.repository.CourseRepository;
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
    private final CourseRepository courseRepository;



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
    public Mono<BatchDto> getBatchById(Long batch_id) {
        return batchRepository.findById(batch_id).map(batch -> modelMapper.map(batch,BatchDto.class));
    }

    @Override
    public Mono<BatchDto> updateBatch(BatchDto batchDto, Long batch_id) {
        return batchRepository.findById(batch_id).flatMap(existing->{
                    existing.setBatch_name(batchDto.getBatch_name());
                    existing.setDescription(batchDto.getDescription());
                    return batchRepository.save(existing);
                })
                .map(updated-> modelMapper.map(updated, BatchDto.class));
    }

    @Override
    public Mono<Void> deleteBatch(Long batch_id) {
        return batchRepository.deleteById(batch_id);
    }

}
