package com.example.student_management_reactive.service;

import com.example.student_management_reactive.dto.StudentInfoDto;
import reactor.core.publisher.Flux;

public interface StudentInfoService {
    public Flux<StudentInfoDto> getAllStudent();
}
