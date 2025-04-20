package com.example.student_management_reactive.service.impl;

import com.example.student_management_reactive.dto.DepartmentDto;
import com.example.student_management_reactive.dto.DepartmentWithCoursesDTO;
import com.example.student_management_reactive.entity.Department;
import com.example.student_management_reactive.repository.CourseRepository;
import com.example.student_management_reactive.repository.DepartmentRepository;
import com.example.student_management_reactive.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;


    @Override
    public Mono<DepartmentDto> createDepartment(DepartmentDto departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        return departmentRepository.save(department)
                .map(saved -> modelMapper.map(saved, DepartmentDto.class));
    }

    @Override
    public Flux<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .map(entity -> modelMapper.map(entity, DepartmentDto.class));
    }

    @Override
    public Mono<DepartmentDto> getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(entity -> modelMapper.map(entity, DepartmentDto.class));
    }

    @Override
    public Mono<DepartmentDto> updateDepartment(Long id, DepartmentDto departmentDTO) {
        return departmentRepository.findById(id)
                .flatMap(existing -> {
                    existing.setDepartmentName(departmentDTO.getDepartmentName());
                    return departmentRepository.save(existing);
                })
                .map(saved -> modelMapper.map(saved, DepartmentDto.class));
    }

    @Override
    public Mono<Void> deleteDepartment(Long id) {
        return departmentRepository.deleteById(id);
    }

    @Override
    public Mono<DepartmentWithCoursesDTO> getDepartmentWithCourses(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .flatMap(dept ->
                        courseRepository.findByDepartmentId(departmentId)
                                .collectList()
                                .map(courses -> new DepartmentWithCoursesDTO(
                                        dept.getDepartmentId(),
                                        dept.getDepartmentName(),
                                        courses
                                ))
                );
    }
}
