package com.example.student_management_reactive.entity;

import com.example.student_management_reactive.dto.CourseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("department")
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    private Long departmentId;
    private String departmentName;
}
