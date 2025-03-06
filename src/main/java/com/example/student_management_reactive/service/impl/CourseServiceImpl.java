package com.example.student_management_reactive.service.impl;

import com.example.student_management_reactive.dto.CourseDto;
import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.entity.Course;
import com.example.student_management_reactive.entity.Student;
import com.example.student_management_reactive.repository.CourseRepository;
import com.example.student_management_reactive.service.CourseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;


    @Override
    public Mono<CourseDto> createCourse(CourseDto courseDto) {
        Course course =modelMapper.map(courseDto,Course.class);
        return courseRepository.save(course).map(crs->modelMapper.map(crs, CourseDto.class));
    }

    @Override
    public Flux<CourseDto> getAllCourse() {
        return courseRepository.findAll().map((crs->modelMapper.map(crs,CourseDto.class)));
    }

    @Override
    public Mono<CourseDto> getCourseById(Long id) {
        return courseRepository.findById(id).map(crs->modelMapper.map(crs,CourseDto.class));
    }

    @Override
    public Mono<CourseDto> updateCourse(CourseDto courseDto, Long id) {
        return courseRepository.findById(id).flatMap(existing->{
                    existing.setCourse_name(courseDto.getCourse_name());
                    existing.setDescription(courseDto.getDescription());
                    existing.setDuration(courseDto.getDuration());
                    return courseRepository.save(existing);
                })
                .map(updated-> modelMapper.map(updated,CourseDto.class));
    }

    @Override
    public Mono<Void> deleteCourse(Long id) {
        return courseRepository.deleteById(id);
    }


}
