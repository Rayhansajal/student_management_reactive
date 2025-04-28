package com.example.student_management_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("results")
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
   private Long id;
   private Long studentId;
   private Long courseId;

   private Double marks;
   private String grade;
   private Boolean pass;
   private LocalDate resultDate;
}
