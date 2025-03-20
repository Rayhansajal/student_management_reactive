package com.example.student_management_reactive.config;

import com.example.student_management_reactive.dto.BatchDto;
import com.example.student_management_reactive.dto.CombinedBatchCourseDTO;
import com.example.student_management_reactive.dto.CourseDto;
import com.example.student_management_reactive.entity.Batch;
import com.example.student_management_reactive.entity.Course;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public DataMapper dataMapper() {
        return new DataMapper() {
            @Override
            public BatchDto batchToBatchDto(Batch batch) {
                return null;
            }

            @Override
            public CourseDto courseToCourseDto(Course course) {
                return null;
            }

            @Override
            public CombinedBatchCourseDTO combineToCombinedBatchCourseDTO(BatchDto batchDto, CourseDto courseDto) {
                return null;
            }
        }; // Manually instantiate the generated implementation
    }
}
