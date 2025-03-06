package com.example.student_management_reactive.service.impl;

import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.entity.Student;
import com.example.student_management_reactive.repository.StudentRepository;
import com.example.student_management_reactive.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Mono<StudentDto> createStudent(StudentDto studentDto) {
        Student student=modelMapper.map(studentDto,Student.class);
        return studentRepository.save(student).map(std->modelMapper.map(std,StudentDto.class));

    }

    @Override
    public Flux<StudentDto> getAllStudent() {
        return studentRepository.findAll().map((std->modelMapper.map(std,StudentDto.class)));
    }

    @Override
    public Mono<StudentDto> getStudentById(Long id) {
        return studentRepository.findById(id).map(std->modelMapper.map(std,StudentDto.class));
    }

    @Override
    public Mono<StudentDto> updateStudent(StudentDto studentDto, Long id) {
        return studentRepository.findById(id).flatMap(existing->{
            existing.setName(studentDto.getName());
            existing.setAge(studentDto.getAge());
            existing.setEmail(studentDto.getEmail());
            existing.setAddress(studentDto.getAddress());
            return studentRepository.save(existing);
        })
                .map(updated-> modelMapper.map(updated,StudentDto.class));
    }

    @Override
    public Mono<Void> deleteStudent(Long id) {
            return studentRepository.deleteById(id);

    }

    @Override
    public Mono<StudentDto> findStudentByName(String name) {
        return studentRepository.findByName(name).map(std->modelMapper.map(std,StudentDto.class));
    }

    @Override
    public Flux<StudentDto> searchStudentByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name).map(std->modelMapper.map(std,StudentDto.class));
    }

    @Override
    public Flux<StudentDto> searchStudentByNameAndAge(String name, int age) {
        return studentRepository.findByNameAndAge(name ,age).map(std->modelMapper.map(std,StudentDto.class));
    }


}
