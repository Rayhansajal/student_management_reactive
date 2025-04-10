package com.example.student_management_reactive.service.impl;

import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;

public class AuditorAwareImpl implements ReactiveAuditorAware<String> {
    @Override
    public Mono<String> getCurrentAuditor() {
        return ReactiveSecurityContextHolder.getContext()
                .map(context -> context.getAuthentication().getName())
                .defaultIfEmpty("SYSTEM");
    }
}
