//package com.example.student_management_reactive.service.impl;
//
//
//import com.example.student_management_reactive.dto.CombinedBatchCourseDTO;
//import com.example.student_management_reactive.entity.Batch;
//import com.example.student_management_reactive.entity.Course;
//import com.example.student_management_reactive.repository.BatchRepository;
//import com.example.student_management_reactive.repository.CourseRepository;
//import com.example.student_management_reactive.service.CombinedBatchCourseService;
//import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Service
//@AllArgsConstructor
//public class CombinedBatchCourseServiceImpl implements CombinedBatchCourseService {
//    private final BatchRepository batchRepository;
//    private final CourseRepository courseRepository;
//
//    private final ModelMapper modelMapper;
//
//
//
//    @Override
//    public Flux<CombinedBatchCourseDTO> getCoursesAndBatches() {
//        // Fetch all courses and batches reactively
//        Flux<Course> courses = courseRepository.findAll();
//        Flux<Batch> batches = batchRepository.findAll();
//
//        return courses.flatMap(course ->
//                batches.filter(batch -> batch.getBatch_id().equals(course.getCourse_id()))  // Match batches with the current course
//                        .collectList()  // Collect matching batches into a list
//                        .map(batchesForCourse -> {
//                            // Map Course to CombinedBatchCourseDTO and set the batches list manually
//                            CombinedBatchCourseDTO dto = modelMapper.map(course, CombinedBatchCourseDTO.class);
//                            dto.setBatches(batchesForCourse);  // Set the list of batches for this course
//                            return dto;
//                        })
//        );
//    }
//
//    @Override
//    public Mono<CombinedBatchCourseDTO> getCourseAndBatchesById(Long course_id) {
//
//        Mono<Course> courseMono = courseRepository.findById(course_id);
//
//
//        Flux<Batch> batchesFlux = batchRepository.findAll()
//                .filter(batch -> batch.getBatch_id().equals(course_id));  // Match batches with the courseId
//
//
//        return courseMono.flatMap(course ->
//                batchesFlux.collectList()  // Collect all matching batches into a list
//                        .map(batchesForCourse -> {
//                            CombinedBatchCourseDTO dto = modelMapper.map(course, CombinedBatchCourseDTO.class);
//                            dto.setBatches(batchesForCourse);  // Set batches for the specific course
//                            return dto;
//                        })
//        );
//    }
//
//}
//
//
//
//
//
