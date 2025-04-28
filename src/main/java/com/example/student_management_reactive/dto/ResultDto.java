package com.example.student_management_reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    private Long id;
    private UUID studentId;
    private Long courseId;

    private Double marks;
    private String grade;
    private Boolean pass;
    private LocalDate resultDate;
}
