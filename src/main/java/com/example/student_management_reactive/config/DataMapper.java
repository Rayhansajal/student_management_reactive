//package com.example.student_management_reactive.config;
//
//import com.example.student_management_reactive.dto.BatchDto;
//import com.example.student_management_reactive.dto.CombinedBatchCourseDTO;
//import com.example.student_management_reactive.dto.CourseDto;
//import com.example.student_management_reactive.entity.Batch;
//import com.example.student_management_reactive.entity.Course;
//import org.mapstruct.Mapper;
//
//
//
//@Mapper(componentModel = "spring")
//public interface DataMapper {
//
//    BatchDto batchToBatchDto(Batch batch);
//
//    // Mapping Course to CourseDTO
//    CourseDto courseToCourseDto(Course course);
//
//    // Combining BatchDTO and CourseDTO into CombinedBatchCourseDTO
//    CombinedBatchCourseDTO combineToCombinedBatchCourseDTO(BatchDto batchDto, CourseDto courseDto);
//}
