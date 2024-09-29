package com.donajul.gateway.filter;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class AuthGatewayFilter implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        if (("test-user".equals(name) && "test-pwd".equals(password))) {
            return Mono.just(new UsernamePasswordAuthenticationToken(name, password, Collections.emptyList()));
        }
        return null;
    }


}
