package com.donajul.gateway.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    public static final String ACCESS_TOKEN_HEADER = "Authorization";

//    private final ServerAuthenticationFailureHandler authenticationFailureHandler
//            = new ServerAuthenticationEntryPointFailureHandler(new HttpBasicServerAuthenticationEntryPoint());

    // 토큰의 인증정보를 SecurityContext에 저장하는 역할 수행
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();
        List<HttpCookie> cookie = cookies.get(ACCESS_TOKEN_HEADER);

        try {

            if (cookie.size() == 1) {
                String jwt = resolveToken(cookie);

//                if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
////                    Authentication authentication = tokenProvider.getAuthentication(jwt);
////                    SecurityContextHolder.getContext().setAuthentication(authentication);
//
////                    return chain.filter(exchange)
////                            .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
//
//                    return chain.filter(exchange);
//                }

            }
        } catch (Exception e) {
            // 예외 발생 시, 인증 실패 처리
        }

//        return this.authenticationFailureHandler
//                .onAuthenticationFailure(new WebFilterExchange(exchange, chain), new JwtAuthenticationException("토큰이 이상해"));
        return null;
    }

    // Request Header 에서 토큰 정보를 꺼내오기 위한 메소드
    private String resolveToken(List<HttpCookie> cookie) {
        String bearerToken = cookie.get(0).getValue();

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

}
