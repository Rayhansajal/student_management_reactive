package com.example.student_management_reactive.service.impl;

import com.example.student_management_reactive.dto.*;
import com.example.student_management_reactive.repository.CourseRepository;
import com.example.student_management_reactive.repository.DepartmentRepository;
import com.example.student_management_reactive.repository.ResultRepository;
import com.example.student_management_reactive.repository.StudentRepository;
import com.example.student_management_reactive.service.StudentInfoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentInfoServiceImpl implements StudentInfoService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    private final ResultRepository resultRepository;

    @Override
    public Flux<StudentInfoDto> getAllStudent() {

//            return studentRepository.findAll()
//                    .flatMap(student -> {
//                        Long deptId = student.getDepartmentId(); // Ensure this exists in Student
//
//                        Mono<DepartmentDto> departmentMono = departmentRepository.findById(deptId)
//                                .map(department -> modelMapper.map(department, DepartmentDto.class));
//
//                        Mono<List<CourseDto>> courseDtosMono = courseRepository.findByDepartmentId(deptId)
//                                .map(course -> modelMapper.map(course, CourseDto.class))
//                                .collectList();
//
//                        Mono<List<ResultDto>> resultListMono = resultRepository.findByStudentId(student.getId())
//                                .map(rs->modelMapper.map(rs, ResultDto.class))
//                                .collectList();
//
//                        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
//
//                        return Mono.zip(departmentMono, courseDtosMono, resultListMono)
//                                .map(tuple -> new StudentInfoDto(studentDto, tuple.getT1(), tuple.getT2(), tuple.getT3()));
//                    });
//        }

        return studentRepository.findAll()
                .flatMap(student -> {
                    Long deptId = student.getDepartmentId();
                    UUID studentId = student.getId(); // <-- FIX: Use correct getter here!

                    Mono<DepartmentDto> departmentMono = departmentRepository.findById(deptId)
                            .map(department -> modelMapper.map(department, DepartmentDto.class));

                    Mono<List<CourseDto>> courseDtosMono = courseRepository.findByDepartmentId(deptId)
                            .map(course -> modelMapper.map(course, CourseDto.class))
                            .collectList();

                    Mono<ResultDto> resultMono = resultRepository.findByStudentId(studentId)
                            .next() // take only one Result
                            .map(result -> modelMapper.map(result, ResultDto.class));

                    StudentDto studentDto = modelMapper.map(student, StudentDto.class);

                    return Mono.zip(departmentMono, courseDtosMono, resultMono)
                            .map(tuple -> new StudentInfoDto(
                                    studentDto,
                                    tuple.getT1(),
                                    tuple.getT2(),
                                    tuple.getT3()
                            ));
                });
    }
}

