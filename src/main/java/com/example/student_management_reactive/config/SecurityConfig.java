package com.example.student_management_reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;

@Configuration
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                         ReactiveAuthenticationManager authenticationManager,
                                                         ServerSecurityContextRepository securityContextRepository) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**").permitAll()


                        // ADMIN can create, update, delete

                        .pathMatchers(HttpMethod.PUT, "/student/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/student/**").hasRole("ADMIN")



                        // USER (and ADMIN) can view
                        .pathMatchers(HttpMethod.GET, "/student/**").hasAnyRole("USER", "ADMIN")
                        .pathMatchers(HttpMethod.POST, "/student/**").hasAnyRole("USER","ADMIN")
                        .anyExchange().authenticated()
                )
                .build();
    }
}

