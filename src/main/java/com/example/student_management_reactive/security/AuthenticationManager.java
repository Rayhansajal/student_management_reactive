package com.example.student_management_reactive.security;

import com.example.student_management_reactive.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        if (jwtUtil.isTokenValid(token)) {
            String username = jwtUtil.getUsernameFromToken(token);

            return userRepository.findByUsername(username)
                    .map(user -> {
                        UserDetails userDetails = User.withUsername(user.getUsername())
                                .password("") // No password needed for JWT
                                .roles(user.getRole().replace("ROLE_", ""))
                                .build();
                        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    });
        }

        return Mono.empty();
    }











//    @Override
//    public Mono<Authentication> authenticate(Authentication authentication) {
//        String token = authentication.getCredentials().toString();
//
//        if (jwtUtil.isTokenValid(token)) {
//            String username = jwtUtil.getUsernameFromToken(token);
//            UserDetails userDetails = User.withUsername(username)
//                    .password("") // No password needed for JWT-based auth
//                    .roles("ADMIN") // Optionally load roles from DB
//                    .build();
//            return Mono.just(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
//        }
//
//        return Mono.empty();
//    }





}
