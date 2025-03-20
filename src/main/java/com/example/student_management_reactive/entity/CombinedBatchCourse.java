package com.example.student_management_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CombinedBatchCourse {

    private Course course;
    private List<Batch> batches;


}
