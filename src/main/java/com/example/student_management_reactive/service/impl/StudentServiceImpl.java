package com.example.student_management_reactive.service.impl;
import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.entity.Student;
import com.example.student_management_reactive.repository.StudentRepository;
import com.example.student_management_reactive.repository.UserRepository;
import com.example.student_management_reactive.security.AuthenticatedUserService;
import com.example.student_management_reactive.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.rmi.StubNotFoundException;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    private final AuthenticatedUserService userService;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);



//    @Override
//    public Mono<StudentDto> createStudent(StudentDto dto) {
//        Student student = modelMapper.map(dto, Student.class);
//
//        return userService.getAuthenticatedUserName()
//                .flatMap(username -> {
//                    student.setCreatedBy(username);
//                    student.setCreatedAt(LocalDateTime.now());
//                    logger.info("Creating student: {} by {}", student.getFirstName(), username);
//                    return studentRepository.save(student);
//                })
//                .map(saved -> modelMapper.map(saved, StudentDto.class));
//    }

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
        return studentRepository.findById(id)
                .map(std->modelMapper.map(std,StudentDto.class));
    }

    @Override
    public Mono<StudentDto> updateStudent(StudentDto studentDto, Long id) {
        return studentRepository.findById(id)
                .flatMap(existing->{
                    existing.setRoll_number(studentDto.getRoll_number());
                    existing.setFirstName(studentDto.getFirstName());
                    existing.setLastName(studentDto.getLastName());
                    existing.setGender(studentDto.getGender());
                    existing.setAge(studentDto.getAge());
                    existing.setEmail(studentDto.getEmail());
                    existing.setAddress(studentDto.getAddress());
                    existing.setDateOfBirth(studentDto.getDateOfBirth());
            return studentRepository.save(existing)
                    .map(updated-> modelMapper
                    .map(updated,StudentDto.class));
        })
                .switchIfEmpty(Mono.error(new StubNotFoundException("Student not found with ID: " + id)));

    }


    @Override
    public Mono<Void> deleteStudent(Long id) {
        return studentRepository.findById(id)
                .flatMap(studentRepository::delete
                )
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found")));
    }

    @Override
    public Mono<StudentDto> findStudentByName(String firstName) {
        return studentRepository.findByFirstName(firstName)
                .map(std->modelMapper.map(std,StudentDto.class));
    }

    @Override
    public Flux<StudentDto> searchStudentByName(String firstName) {
        return studentRepository.findByFirstNameContainingIgnoreCase(firstName)
                .map(std->modelMapper.map(std,StudentDto.class));
    }

    @Override
    public Flux<StudentDto> searchStudentByNameAndAge(String firstName, int age) {

        if(firstName.length()<3) {
            return Flux.error(new IllegalArgumentException("search must be at least" + 3 + "characters long"));
        }
        String searchPattern = "%" + firstName + "%";
        return studentRepository.findByNameAndAge(searchPattern ,age)
                .map(std->modelMapper.map(std,StudentDto.class));
    }


}






