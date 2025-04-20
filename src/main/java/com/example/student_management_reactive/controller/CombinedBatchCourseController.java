//package com.example.student_management_reactive.controller;
//
//import com.example.student_management_reactive.dto.CombinedBatchCourseDTO;
//import com.example.student_management_reactive.service.CombinedBatchCourseService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@AllArgsConstructor
//@RestController
//@RequestMapping("/combined")
//public class CombinedBatchCourseController {
//    private final CombinedBatchCourseService combinedBatchCourseService;
//
//
//    @GetMapping()
//    public Flux<CombinedBatchCourseDTO> getCoursesAndBatches() {
//        return combinedBatchCourseService.getCoursesAndBatches();
//    }
//
//    @GetMapping("/courses/{course_id}/batches")
//    public Mono<ResponseEntity<CombinedBatchCourseDTO>> getCourseAndBatches(@PathVariable Long course_id) {
//        return combinedBatchCourseService.getCourseAndBatchesById(course_id)
//                .map(ResponseEntity::ok)
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }
//}
