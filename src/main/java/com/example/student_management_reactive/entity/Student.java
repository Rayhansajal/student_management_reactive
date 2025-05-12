package com.example.student_management_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("students")
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity{

    @Id
    private UUID id;
    private Long rollNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String email;
    private String mobile;
    private String address;
    private LocalDate dateOfBirth;
// foreign key
    private Long departmentId;

}
