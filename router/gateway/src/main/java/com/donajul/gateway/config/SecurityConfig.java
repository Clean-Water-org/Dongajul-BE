package com.donajul.gateway.config;

import com.donajul.gateway.filter.JwtAuthenticationFilter;
import com.donajul.gateway.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    // ServerHttpSecurity
    //Spring Security가 WebFlux 애플리케이션에 대한 보안 규칙을 구성하는 데 제공하는 빌더 객체입니다.
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange(exchange -> exchange.pathMatchers("/api/public/**")
                .permitAll()
                .anyExchange()
                .authenticated()
        ).httpBasic(Customizer.withDefaults()).build();
    }

}
