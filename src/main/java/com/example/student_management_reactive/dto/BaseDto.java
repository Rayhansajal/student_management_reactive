package com.example.student_management_reactive.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseDto {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
