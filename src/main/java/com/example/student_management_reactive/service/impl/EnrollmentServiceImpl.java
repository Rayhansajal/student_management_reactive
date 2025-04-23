package com.example.student_management_reactive.service.impl;

import com.example.student_management_reactive.dto.CourseDto;
import com.example.student_management_reactive.dto.DepartmentDto;
import com.example.student_management_reactive.dto.EnrollmentDto;
import com.example.student_management_reactive.dto.StudentDto;
import com.example.student_management_reactive.entity.Course;
import com.example.student_management_reactive.entity.Department;
import com.example.student_management_reactive.entity.Enrollment;
import com.example.student_management_reactive.entity.Student;
import com.example.student_management_reactive.repository.CourseRepository;
import com.example.student_management_reactive.repository.DepartmentRepository;
import com.example.student_management_reactive.repository.EnrollmentRepository;
import com.example.student_management_reactive.repository.StudentRepository;
import com.example.student_management_reactive.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(EnrollmentServiceImpl.class);



    @Override
    public Mono<EnrollmentDto> enrollStudent(EnrollmentDto dto) {
        Long studentId = dto.getStudentId();
        Long departmentId = dto.getDepartmentId();
        List<Long> courseIds = dto.getCourseIds();

        if (studentId == null || departmentId == null || courseIds == null || courseIds.isEmpty()) {
            return Mono.error(new IllegalArgumentException("Missing required fields"));
        }

        // Check if student exists
        return studentRepository.findById(studentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Student not found")))
                .flatMap(student -> {
                    // Update student's department
                    student.setDepartmentId(departmentId);
                    return studentRepository.save(student);
                })
                .flatMap(updatedStudent -> {
                    // Check for existing enrollments for any of the courses
                    return Flux.fromIterable(courseIds)
                            .flatMap(courseId -> enrollmentRepository.existsByStudentIdAndCourseIdAndDepartmentId(studentId, courseId, departmentId))
                            .collectList()
                            .flatMap(existsList -> {
                                if (existsList.contains(true)) {
                                    return Mono.error(new IllegalStateException("Student already enrolled in one or more of these courses"));
                                }
                                return Mono.just(updatedStudent);
                            });
                })
                .flatMap(updatedStudent -> {
                    // Create enrollments for all courses
                    List<Enrollment> enrollments = courseIds.stream()
                            .map(courseId -> {
                                Enrollment enrollment = new Enrollment();
                                enrollment.setStudentId(studentId);
                                enrollment.setDepartmentId(departmentId);
                                enrollment.setCourseId(courseId);
                                enrollment.setEnrollmentDate(LocalDate.now());
                                return enrollment;
                            })
                            .collect(Collectors.toList());

                    return enrollmentRepository.saveAll(enrollments).collectList();
                })
                .flatMap(savedEnrollments -> {



                    // Fetch all related data for response
                    Mono<Student> studentMono = studentRepository.findById(studentId);
                    Mono<Department> departmentMono = departmentRepository.findById(departmentId);
                    Flux<Course> coursesFlux = Flux.fromIterable(courseIds)
                            .flatMap(courseRepository::findById);

                    return Mono.zip(studentMono, departmentMono, coursesFlux.collectList())
                            .map(tuple -> {
                                Student student = tuple.getT1();
                                Department department = tuple.getT2();
                                List<Course> courses = tuple.getT3();

                                EnrollmentDto response = new EnrollmentDto();

                                // Set the IDs explicitly
                                response.setStudentId(studentId);
                                response.setDepartmentId(departmentId);
                                response.setCourseIds(courseIds);  // This should be List<Long>

                                // Set the nested objects
                                response.setStudent(modelMapper.map(student, StudentDto.class));
                                response.setDepartment(modelMapper.map(department, DepartmentDto.class));
                                response.setCourses(courses.stream()
                                        .map(course -> modelMapper.map(course, CourseDto.class))
                                        .collect(Collectors.toList()));

                                response.setEnrollmentDate(LocalDate.now());
                                return response;
                            });
                });
    }

    @Override
    public Mono<EnrollmentDto> getEnrollmentDetailsByStudentId(Long studentId) {
        return studentRepository.findById(studentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Student not found")))
                .flatMap(student -> {
                    // Get department
                    Mono<Department> departmentMono = departmentRepository.findById(student.getDepartmentId())
                            .switchIfEmpty(Mono.error(new IllegalArgumentException("Department not found")));

                    // Get all enrollments and courses
                    Flux<Course> courseFlux = enrollmentRepository.findAllByStudentId(studentId)
                            .flatMap(enrollment -> courseRepository.findById(enrollment.getCourseId())
                                    .switchIfEmpty(Mono.error(new IllegalArgumentException("Course not found"))));

                    // Collect course IDs
                    Mono<List<Long>> courseIdsMono = enrollmentRepository.findAllByStudentId(studentId)
                            .map(Enrollment::getCourseId)
                            .collectList();

                    return Mono.zip(Mono.just(student), departmentMono, courseFlux.collectList(), courseIdsMono)
                            .map(tuple -> {
                                Student s = tuple.getT1();
                                Department department = tuple.getT2();
                                List<Course> courses = tuple.getT3();
                                List<Long> courseIds = tuple.getT4();

                                EnrollmentDto dto = new EnrollmentDto();
                                // Set IDs explicitly
                                dto.setStudentId(studentId);
                                dto.setDepartmentId(s.getDepartmentId());
                                dto.setCourseIds(courseIds);

                                // Set nested objects
                                dto.setStudent(modelMapper.map(s, StudentDto.class));
                                dto.setDepartment(modelMapper.map(department, DepartmentDto.class));
                                dto.setCourses(courses.stream()
                                        .map(course -> modelMapper.map(course, CourseDto.class))
                                        .toList());

                                // Set enrollment date (you might want to get this from enrollments)
                                dto.setEnrollmentDate(LocalDate.now());
                                return dto;
                            });
                });
    }

    @Override
    public Mono<EnrollmentDto> updateEnrollment(Long enrollmentId, EnrollmentDto dto) {
        return enrollmentRepository.findById(enrollmentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Enrollment not found")))
                .flatMap(existingEnrollment -> {
                    // Verify student exists
                    return studentRepository.findById(dto.getStudentId())
                            .switchIfEmpty(Mono.error(new IllegalArgumentException("Student not found")))
                            .flatMap(student -> {
                                // Verify department exists
                                return departmentRepository.findById(dto.getDepartmentId())
                                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Department not found")));
                            })
                            .then(courseRepository.findById(dto.getCourseIds().get(0)) // Assuming single course update
                                    .switchIfEmpty(Mono.error(new IllegalArgumentException("Course not found"))))
                            .then(Mono.defer(() -> {
                                // Update enrollment
                                existingEnrollment.setStudentId(dto.getStudentId());
                                existingEnrollment.setDepartmentId(dto.getDepartmentId());
                                existingEnrollment.setCourseId(dto.getCourseIds().get(0));
                                existingEnrollment.setEnrollmentDate(LocalDate.now());

                                return enrollmentRepository.save(existingEnrollment);
                            }));
                })
                .flatMap(updatedEnrollment -> {
                    // Fetch all related data for response
                    Mono<Student> studentMono = studentRepository.findById(updatedEnrollment.getStudentId());
                    Mono<Department> departmentMono = departmentRepository.findById(updatedEnrollment.getDepartmentId());
                    Mono<Course> courseMono = courseRepository.findById(updatedEnrollment.getCourseId());

                    return Mono.zip(studentMono, departmentMono, courseMono)
                            .map(tuple -> {
                                EnrollmentDto response = new EnrollmentDto();
                                response.setStudentId(updatedEnrollment.getStudentId());
                                response.setDepartmentId(updatedEnrollment.getDepartmentId());
                                response.setCourseIds(List.of(updatedEnrollment.getCourseId()));

                                response.setStudent(modelMapper.map(tuple.getT1(), StudentDto.class));
                                response.setDepartment(modelMapper.map(tuple.getT2(), DepartmentDto.class));
                                response.setCourses(List.of(modelMapper.map(tuple.getT3(), CourseDto.class)));
                                response.setEnrollmentDate(updatedEnrollment.getEnrollmentDate());

                                return response;
                            });
                });
    }

    @Override
    public Mono<Void> deleteEnrollment(Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Enrollment not found")))
                .flatMap(enrollment -> {
                    // You might want to add additional business logic here
                    // For example, check if deletion is allowed

                    return enrollmentRepository.delete(enrollment)
                            .then(Mono.fromRunnable(() ->
                                    log.info("Deleted enrollment with ID: {}", enrollmentId)));
                });
    }

}
