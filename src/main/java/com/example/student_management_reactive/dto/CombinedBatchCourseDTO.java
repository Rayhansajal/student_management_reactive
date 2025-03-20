package com.example.student_management_reactive.dto;

import com.example.student_management_reactive.entity.Batch;
import com.example.student_management_reactive.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CombinedBatchCourseDTO {
    private Course course;
    private List<Batch> batches;

}
