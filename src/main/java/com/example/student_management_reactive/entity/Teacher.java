package com.example.student_management_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("teacher")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
