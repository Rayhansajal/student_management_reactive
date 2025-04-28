package com.example.student_management_reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {

    private UUID studentId;
    private Long departmentId;
    private List<Long> courseIds;

    private StudentDto student;
    private DepartmentDto department;
    private List<CourseDto> courses;
    private LocalDate enrollmentDate;

}
