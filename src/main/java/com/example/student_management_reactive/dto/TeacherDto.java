package com.example.student_management_reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto extends BaseDto {
    private String name;
    private Integer age;
    private String email;

}
