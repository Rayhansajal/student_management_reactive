package com.example.student_management_reactive.service.impl;

import com.example.student_management_reactive.dto.CourseDto;
import com.example.student_management_reactive.dto.DepartmentDto;
import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.dto.StudentInfoDto;
import com.example.student_management_reactive.repository.CourseRepository;
import com.example.student_management_reactive.repository.DepartmentRepository;
import com.example.student_management_reactive.repository.StudentRepository;
import com.example.student_management_reactive.service.StudentInfoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentInfoServiceImpl implements StudentInfoService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    @Override
    public Flux<StudentInfoDto> getAllStudent() {

            return studentRepository.findAll()
                    .flatMap(student -> {
                        Long deptId = student.getDepartmentId(); // Ensure this exists in Student

                        Mono<DepartmentDto> departmentMono = departmentRepository.findById(deptId)
                                .map(department -> modelMapper.map(department, DepartmentDto.class));

                        Mono<List<CourseDto>> courseDtosMono = courseRepository.findByDepartmentId(deptId)
                                .map(course -> modelMapper.map(course, CourseDto.class))
                                .collectList();

                        StudentDto studentDto = modelMapper.map(student, StudentDto.class);

                        return Mono.zip(departmentMono, courseDtosMono)
                                .map(tuple -> new StudentInfoDto(studentDto, tuple.getT1(), tuple.getT2()));
                    });
        }
    }

