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

import static com.donajul.gateway.filter.HeaderNames.*;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AuthGatewayFilter extends AbstractGatewayFilterFactory<AuthGatewayFilter.Config> {

    private final TokenProvider tokenProvider;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> { // pre-processing
            List<String> tokens = exchange.getRequest().getHeaders().get(TOKEN.getValue());
            List<String> refreshTokens = exchange.getRequest().getHeaders().get(REFRESH_TOKEN.getValue());

            if (CollectionUtils.isEmpty(tokens)) {
                throw new IllegalArgumentException("Token is not valid");
            }

            if (CollectionUtils.isEmpty(refreshTokens)) {
                throw new IllegalArgumentException("Refresh Token is not valid");
            }

            try {
                String token = tokenProvider.readingToken(tokens.get(0), refreshTokens.get(0));
                exchange.getResponse().getHeaders().add(TOKEN.getValue(), token);
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
