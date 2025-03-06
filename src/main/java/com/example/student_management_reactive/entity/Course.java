package com.example.student_management_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("courses")
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    private Long id;
    private String course_name;
    private String description;
    private String duration;
}
