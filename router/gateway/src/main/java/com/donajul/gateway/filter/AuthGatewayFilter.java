package com.donajul.gateway.filter;

import com.donajul.gateway.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AuthGatewayFilter extends AbstractGatewayFilterFactory<AuthGatewayFilter.Config> {

    private static final String EMPTY_STR = "";

    private static final String COLON_STR = ":";

    private static final String BASIC_STR = "Basic ";

    private final String TOKEN_NAME = "token";

    private final TokenProvider tokenProvider;


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> { // pre-processing
            if(!exchange.getRequest().getURI().toString().contains("/login")) {
                String token = exchange.getRequest().getHeaders().get("authorization").get(0);

                if(!tokenProvider.validateToken(token)) {
                    // return Mono.error(new JwtAuthenticationException("Token is not valid"));
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                  //  return exchange.getResponse().setComplete();
                }
            }

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        boolean isAuthorized = true; // todo.
                        if(isAuthorized) {
                            // add response token
                            String token = tokenProvider.createToken();
                            exchange.getResponse().getHeaders().add(TOKEN_NAME, token);
                        }
                        // Post-processing
                        // Add your post-processing logic here
                    }));
        };
//        return (exchange, chain) -> {
//            if (exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//
//                String basicAuthValue = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//                basicAuthValue = basicAuthValue != null ? basicAuthValue.replace(BASIC_STR, EMPTY_STR) : EMPTY_STR;
//
//                basicAuthValue = new String(Base64.getDecoder().decode(basicAuthValue.getBytes()));
//                String[] credentials = basicAuthValue.split(COLON_STR);
//                if (credentials.length == 2) {
//                    String userName = credentials[0];
//                    String password = credentials[1];
//
//                    //Check credentials with difference sources like database, LDAP, static files, etc
//                    //For demonstration purpose, here we are validating credentials with hard code values.
////                    if (userName.equals("test-user") && password.equals("test-pwd")) {
////                        return chain.filter(exchange);
////                    }
//                    // login 처리는 USER 서비스에서 처리
//                }
//            }
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        };


    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    static class Config {

    }
}
