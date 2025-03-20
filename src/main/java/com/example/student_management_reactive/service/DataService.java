package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.CombinedBatchCourseDTO;
import reactor.core.publisher.Flux;

public interface DataService {

    public Flux<CombinedBatchCourseDTO> getCoursesAndBatches();

}
