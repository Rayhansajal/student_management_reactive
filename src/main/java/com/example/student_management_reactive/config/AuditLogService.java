package com.example.student_management_reactive.config;

import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.entity.Student;
import com.example.student_management_reactive.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuditLogService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    public Mono<StudentDto> saveStudent(StudentDto studentDto) {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .defaultIfEmpty("system") // Default to "system" if no authenticated user
                .flatMap(username -> {
                    // Convert StudentDto to Student entity
                    Student student = modelMapper.map(studentDto, Student.class);

                    // Set createdBy for new students or updatedBy for existing students
                    if (student.getCreatedAt() == null) {
                        student.setCreatedBy(username);
                        student.setCreatedAt(LocalDateTime.now());
                    } else {
                        student.setUpdatedBy(username);
                        student.setUpdatedAt(LocalDateTime.now());
                    }

                    return studentRepository.save(student)
                            .map(savedStudent -> modelMapper.map(savedStudent, StudentDto.class)); // Return StudentDto
                });
    }



}
