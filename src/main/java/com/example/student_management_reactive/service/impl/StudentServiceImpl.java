package com.example.student_management_reactive.service.impl;

import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.entity.Student;
import com.example.student_management_reactive.repository.StudentRepository;
import com.example.student_management_reactive.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.rmi.StubNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final DatabaseClient databaseClient;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);



@Override
public Mono<StudentDto> createStudent(StudentDto studentDto) {
    Student student = modelMapper.map(studentDto, Student.class);
    student.setId(null); // Ensure entity is treated as new
    return studentRepository.save(student)
            .map(savedStudent -> modelMapper.map(savedStudent, StudentDto.class));
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
                    existing.setRollNumber(studentDto.getRollNumber());
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

    @Override
    public Flux<Student> advancedSearch(Student student) {
        StringBuilder query = new StringBuilder("SELECT * FROM students WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if (student.getFirstName() != null) {
            query.append(" AND first_name LIKE :firstName");
            params.put("firstName", "%" + student.getFirstName() + "%");
        }
        if (student.getLastName() != null) {
            query.append(" AND last_name LIKE :lastName");
            params.put("lastName", "%" + student.getLastName() + "%");
        }
        if (student.getGender() != null) {
            query.append(" AND gender = :gender");
            params.put("gender", student.getGender());
        }
        if (student.getAge() != null) {
            query.append(" AND age = :age");
            params.put("age", student.getAge());
        }
        if (student.getEmail() != null) {
            query.append(" AND email LIKE :email");
            params.put("email", "%" + student.getEmail() + "%");
        }
        if (student.getMobile() != null) {
            query.append(" AND mobile LIKE :mobile");
            params.put("mobile", "%" + student.getMobile() + "%");
        }
        if (student.getAddress() != null) {
            query.append(" AND address LIKE :address");
            params.put("address", "%" + student.getAddress() + "%");
        }
        if (student.getDateOfBirth() != null) {
            query.append(" AND date_of_birth = :dob");
            params.put("dob", student.getDateOfBirth());
        }
        if (student.getRollNumber() != null) {
            query.append(" AND roll_number = :rollNumber");
            params.put("rollNumber", student.getRollNumber());
        }

        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(query.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            spec = spec.bind(entry.getKey(), entry.getValue());
        }

        return spec.map((row, meta) -> {
            Student s = new Student();
            s.setId(row.get("student_id", Long.class));
            s.setRollNumber(row.get("roll_number", Long.class));
            s.setFirstName(row.get("first_name", String.class));
            s.setLastName(row.get("last_name", String.class));
            s.setGender(row.get("gender", String.class));
            s.setAge(row.get("age", Integer.class));
            s.setEmail(row.get("email", String.class));
            s.setMobile(row.get("mobile", String.class));
            s.setAddress(row.get("address", String.class));
            s.setDateOfBirth(row.get("date_of_birth", LocalDate.class));
            return s;
        }).all();
    }
}






