package com.example.student_management_reactive.dto;

import com.example.student_management_reactive.entity.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoDto {
    private StudentDto student;
    private DepartmentDto department;
    private List<CourseDto> courses;
    private ResultDto result;
}
