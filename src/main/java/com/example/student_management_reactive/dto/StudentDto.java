package com.example.student_management_reactive.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDto extends BaseDto {

    private Long id;
    private Long rollNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String email;
    private String mobile;
    private String address;
    private LocalDate dateOfBirth;

    private Long departmentId;



}
