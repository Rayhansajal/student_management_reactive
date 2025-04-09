package com.example.student_management_reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

    private Long studentId;
    private Long courseId;
    private String gpa;
}
