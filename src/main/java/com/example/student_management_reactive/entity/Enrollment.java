package com.example.student_management_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("enrollment")
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @Id
    private Long enrollmentId;
    private Department department;
    private Course course;

}
