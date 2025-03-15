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
    public Mono<BatchDto> saveCourse(@RequestBody BatchDto batchDto){
        return batchService.createBatch(batchDto);
    }

    @GetMapping
    public Flux<BatchDto> getAllCourse(){
        return batchService.getAllBatch();
    }
    @GetMapping("/{id}")
    public Mono<BatchDto> getBatchById(@PathVariable Long id) {
        return batchService.getBatchById(id);
    }
    @PutMapping("/{id}")
    public Mono<BatchDto> updateBatch(@PathVariable Long id, @RequestBody BatchDto batchDto){
        return batchService.updateBatch(batchDto,id);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteBatch(@PathVariable Long id) {
        return batchService.deleteBatch(id);
    }
}
