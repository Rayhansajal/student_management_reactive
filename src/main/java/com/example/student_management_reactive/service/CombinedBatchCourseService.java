package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.CombinedBatchCourseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CombinedBatchCourseService {

     Flux<CombinedBatchCourseDTO> getCoursesAndBatches();
     Mono<CombinedBatchCourseDTO> getCourseAndBatchesById(Long course_id);

}
