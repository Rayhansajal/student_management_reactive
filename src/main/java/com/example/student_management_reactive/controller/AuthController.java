package com.example.student_management_reactive.controller;

import com.example.student_management_reactive.dto.LoginRequestDto;
import com.example.student_management_reactive.dto.RegisterRequestDto;
import com.example.student_management_reactive.entity.User;
import com.example.student_management_reactive.repository.UserRepository;
import com.example.student_management_reactive.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;



    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody LoginRequestDto request) {
        return userRepository.findByUsername(request.getUsername())
                .flatMap(user -> {
                    if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                        String token = jwtUtil.generateToken(user.getUsername());
                        Map<String, String> response = Map.of("token", token);
                        return Mono.just(ResponseEntity.ok(response));
                    }
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).<Map<String, String>>build());
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).<Map<String, String>>build()));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<String>> register(@RequestBody RegisterRequestDto request) {
        return userRepository.findByUsername(request.getUsername())
                .flatMap(existing -> Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("User already exists")))
                .switchIfEmpty(Mono.defer(() -> {
                    User user = new User(null, request.getUsername(),
                            passwordEncoder.encode(request.getPassword()), "ROLE_USER");
                    return userRepository.save(user)
                            .thenReturn(ResponseEntity.ok("User registered successfully"));
                }));
    }
}
