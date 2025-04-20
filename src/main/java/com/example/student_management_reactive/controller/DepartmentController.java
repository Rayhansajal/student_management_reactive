package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.dto.DepartmentDto;
import com.example.student_management_reactive.dto.DepartmentWithCoursesDTO;
import com.example.student_management_reactive.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;



    @PostMapping
    public Mono<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        return departmentService.createDepartment(departmentDto);
    }

    @GetMapping
    public Flux<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<DepartmentDto>> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<DepartmentDto>> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto dto) {
        return departmentService.updateDepartment(id, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id)
                .thenReturn(ResponseEntity.noContent().build());
    }



    @GetMapping("/{id}/courses")
    public Mono<DepartmentWithCoursesDTO> getDepartmentWithCourses(@PathVariable Long id) {
        return departmentService.getDepartmentWithCourses(id);
    }
}
