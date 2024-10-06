package com.donajul.gateway.config;

import com.donajul.gateway.filter.AuthTokenIssuerFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final String tokenIssueUri = "/**/login";
    private final AuthTokenIssuerFilter authTokenIssuerFilter;

    public GatewayConfig(AuthTokenIssuerFilter authTokenIssuerFilter) {
        this.authTokenIssuerFilter = authTokenIssuerFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/sample/login")
                        .filters(f -> f.filter(authTokenIssuerFilter.apply(new AuthTokenIssuerFilter.Config())))
                        .uri("lb://sample-service")) // 서비스
                .build();
    }
}
