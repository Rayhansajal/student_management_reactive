package com.example.student_management_reactive.entity;

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
    private Long course_id;
    private String course_name;
    private String course_description;
    private String duration;

}
