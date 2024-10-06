package com.donajul.gateway.filter;

import com.donajul.gateway.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.donajul.gateway.filter.HeaderNames.*;

@RequiredArgsConstructor
@Component
public class AuthTokenIssuerFilter extends AbstractGatewayFilterFactory<AuthTokenIssuerFilter.Config> {

    private final TokenProvider tokenProvider;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> { // pre-processing
            return chain.filter(exchange) // post-processing
                    .then(Mono.fromRunnable(() -> {
                        boolean isAuthorized = exchange.getResponse().getStatusCode() != null
                                && exchange.getResponse().getStatusCode().is2xxSuccessful(); // todo.

                        if(isAuthorized) {
                            exchange.getResponse().getHeaders().add(TOKEN.getValue(), tokenProvider.createToken());
                            exchange.getResponse().getHeaders().add(REFRESH_TOKEN.getValue(), tokenProvider.createRefreshToken());
                            exchange.getResponse().getHeaders().add(PUBLIC_KEY.getValue(), tokenProvider.getPublicKey());

                        } else {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        }
                    }));
        };
    }

    @Override
    public Class<AuthTokenIssuerFilter.Config> getConfigClass() {
        return AuthTokenIssuerFilter.Config.class;
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }

}
