package com.example.student_management_reactive.service.impl;

import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.dto.TeacherDto;
import com.example.student_management_reactive.entity.Student;
import com.example.student_management_reactive.entity.Teacher;
import com.example.student_management_reactive.repository.TeacherRepository;
import com.example.student_management_reactive.service.TeacherService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    @Override
    public Mono<TeacherDto> createTeacher(TeacherDto teacherDto) {
        Teacher teacher =modelMapper.map(teacherDto,Teacher.class);
        return teacherRepository.save(teacher).map(th->modelMapper.map(th,TeacherDto.class));
    }

    @Override
    public Flux<TeacherDto> getAllTeacher() {
        return teacherRepository.findAll().map((th->modelMapper.map(th,TeacherDto.class)));
    }

    @Override
    public Mono<TeacherDto> getStudentById(Long id) {
        return teacherRepository.findById(id).map(th->modelMapper.map(th,TeacherDto.class));
    }

    @Override
    public Mono<TeacherDto> updateStudent(TeacherDto teacherDto, Long id) {
        return teacherRepository.findById(id).flatMap(existing->{
                    existing.setName(teacherDto.getName());
                    existing.setAge(teacherDto.getAge());
                    existing.setEmail(teacherDto.getEmail());
                    return teacherRepository.save(existing);
                })
                .map(updated-> modelMapper.map(updated,TeacherDto.class));

    }

    @Override
    public Mono<Void> deleteTeacher(Long id) {
        return teacherRepository.deleteById(id);
    }
}
