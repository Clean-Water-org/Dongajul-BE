package com.donajul.gateway.filter;

import com.donajul.gateway.token.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AuthGatewayFilter extends AbstractGatewayFilterFactory<AuthGatewayFilter.Config> {

    private static final String EMPTY_STR = "";

    private static final String COLON_STR = ":";

    private static final String BASIC_STR = "Basic ";

    private final TokenProvider tokenProvider;

    private final String TOKEN_HEADER_NAME = "authorization";


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> { // pre-processing
            List<String> tokens = exchange.getRequest().getHeaders().get(TOKEN_HEADER_NAME);

            if(CollectionUtils.isEmpty(tokens)) {
                throw new IllegalArgumentException("Token is not valid");
            }

            try {
                Jws<Claims> claimsJws = tokenProvider.readingToken(tokens.get(0));
                // exchange.getResponse().getHeaders().set("user", claimsJws.getBody().getSubject());
                return chain.filter(exchange);

            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    static class Config {

    }
}
