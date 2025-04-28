package com.example.student_management_reactive.service.impl;

import com.example.student_management_reactive.dto.ResultDto;
import com.example.student_management_reactive.entity.Result;
import com.example.student_management_reactive.repository.ResultRepository;
import com.example.student_management_reactive.service.ResultService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final ModelMapper modelMapper;

    @Override
    public Mono<ResultDto> saveResult(ResultDto resultDto) {
        return resultRepository.findByStudentIdAndCourseId(
                        resultDto.getStudentId(),
                        resultDto.getCourseId())
                .flatMap(existingResult ->
                        Mono.error(new RuntimeException("Student already has a result for this course")))
                .switchIfEmpty(Mono.defer(() -> {
                    Result result = modelMapper.map(resultDto, Result.class);
                    return resultRepository.save(result)
                            .map(savedResult -> modelMapper.map(savedResult, ResultDto.class));
                }))
                .cast(ResultDto.class);
    }




//    @Override
//    public Mono<ResultDto> saveResult(ResultDto resultDto) {
//        Result result = new Result();
//        result.setStudentId(resultDto.getStudentId());
//        result.setCourseId(resultDto.getCourseId());
//        result.setMarks(resultDto.getMarks());
//        result.setGrade(resultDto.getGrade());
//        result.setPass(resultDto.getPass());
//        result.setResultDate(resultDto.getResultDate());
//
//        return resultRepository.save(result)
//                .map(savedResult -> {
//                    ResultDto dto = new ResultDto();
//                    dto.setId(savedResult.getId());
//                    dto.setStudentId(savedResult.getStudentId());
//                    dto.setCourseId(savedResult.getCourseId());
//                    dto.setMarks(savedResult.getMarks());
//                    dto.setGrade(savedResult.getGrade());
//                    dto.setPass(savedResult.getPass());
//                    dto.setResultDate(savedResult.getResultDate());
//                    return dto;
//                });
//    }

//    @Override
//    public Flux<ResultDto> getResultsByStudentId(Long studentId) {
//        return resultRepository.findByStudentId(studentId)
//                .map(result -> modelMapper.map(result, ResultDto.class));
//    }
//
//    @Override
//    public Flux<ResultDto> getResultsByCourseId(Long courseId) {
//        return resultRepository.findAllByCourseId(courseId)
//                .map(result -> modelMapper.map(result, ResultDto.class));
//    }
}
