package com.example.student_management_reactive.entity;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
public abstract class BaseEntity {

    private Long id;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column("created_by")
    private String createdBy;

    @LastModifiedBy
    @Column("updated_by")
    private String updatedBy;

    @Column("deleted_by")
    private String deletedBy;

    public void markAsDeleted(String username) {
        this.deletedBy = username;
    }
}
