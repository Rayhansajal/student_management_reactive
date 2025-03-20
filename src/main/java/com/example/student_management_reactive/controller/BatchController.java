package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.dto.BatchDto;
import com.example.student_management_reactive.service.BatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@AllArgsConstructor
@RestController
@RequestMapping("/batch")
public class BatchController {
    private final BatchService batchService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BatchDto> saveBatch(@RequestBody BatchDto batchDto){
        return batchService.createBatch(batchDto);
    }

    @GetMapping
    public Flux<BatchDto> getAllBatch(){
        return batchService.getAllBatch();
    }
    @GetMapping("/{batch_id}")
    public Mono<BatchDto> getBatchById(@PathVariable Long batch_id) {
        return batchService.getBatchById(batch_id);
    }
    @PutMapping("/{batch_id}")
    public Mono<BatchDto> updateBatch(@PathVariable Long batch_id, @RequestBody BatchDto batchDto){
        return batchService.updateBatch(batchDto,batch_id);
    }
    @DeleteMapping("/{batch_id}")
    public Mono<Void> deleteBatch(@PathVariable Long batch_id) {
        return batchService.deleteBatch(batch_id);
    }

}
