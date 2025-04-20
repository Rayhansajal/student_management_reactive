package com.example.student_management_reactive.entity;

import com.example.student_management_reactive.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@Table("courses")
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    private Long courseId;
    private String courseName;
    private String courseDescription;
    private String duration;


    private Long departmentId; // FK to Department
}
