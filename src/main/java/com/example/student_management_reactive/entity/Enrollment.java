package com.example.student_management_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("enrollments")
public class Enrollment {
    @Id
    private Long id;

    private UUID studentId;
    private Long courseId;
    private Long departmentId;
    private LocalDate enrollmentDate;


}
