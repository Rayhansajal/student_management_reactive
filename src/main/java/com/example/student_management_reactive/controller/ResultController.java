package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.dto.ResultDto;
import com.example.student_management_reactive.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/result")
@AllArgsConstructor
public class ResultController {
    private final ResultService resultService;
    @PostMapping
    public Mono<ResultDto> saveResult(@RequestBody ResultDto resultDto) {
        return resultService.saveResult(resultDto);
    }

    @GetMapping("/student/{studentId}")
    public Flux<ResultDto> getResultsByStudent(@PathVariable UUID studentId) {
        return resultService.getResultsByStudentId(studentId);
    }

    @GetMapping("/course/{courseId}")
    public Flux<ResultDto> getResultsByCourse(@PathVariable Long courseId) {
        return resultService.getResultsByCourseId(courseId);
    }
}
