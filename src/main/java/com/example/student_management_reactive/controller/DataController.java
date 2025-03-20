package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.dto.CombinedBatchCourseDTO;
import com.example.student_management_reactive.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@RestController
@RequestMapping("/data")
public class DataController {
    private final DataService dataService;



//    @GetMapping
//    public Flux<Tuple2<Batch, Course>> getCombineBatchCourseData(){
//        return dataService.getCombinedBatchCourseData();
//    }

    @GetMapping()
    public Flux<CombinedBatchCourseDTO> getCoursesAndBatches() {
        return dataService.getCoursesAndBatches();
    }
}
