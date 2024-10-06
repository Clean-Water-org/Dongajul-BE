package com.donajul.gateway.token;

import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";

    private final long tokenValidityInMilliseconds;
    private final KeyPair keyPair;


    public TokenProvider(@Value("${spring.jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.keyPair = Jwts.SIG.RS256.keyPair().build();
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    public String createToken() {
        long now = (new Date()).getTime();
        Date validity = new Date(now + tokenValidityInMilliseconds);

        return Jwts.builder()
                .signWith(keyPair.getPrivate(), Jwts.SIG.RS256)
                .claim(AUTHORITIES_KEY, "헤헤")
                .expiration(validity)
                .compact();
    }

    public Jws<Claims> readingToken(String token) {

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(keyPair.getPublic())
                    .build()
                    .parseSignedClaims(token);
            System.out.println(claimsJws.toString());
            return claimsJws;

        } catch (JwtException | IllegalArgumentException e) {
            throw e;
        }
    }


    // 토큰으로 클레임을 만들고 이를 이용해 유저 객체를 만들어서 최종적으로 authentication 객체를 리턴
//    public Authentication getAuthentication(String token) {
//        Claims claims = Jwts
//                .parser()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        // todo.
//        User principal = new User();
//
//        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
//    }



//    public static void main(String[] args) {
//        long now = (new Date()).getTime();
//        Date validity = new Date(now + 1000000);
//        String secret = "a2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbQ==";
//
//        SecretKey secretKey1 = Jwts.SIG.HS512.key().build();
//
//        String token =  Jwts.builder()
//                .subject("Joe")
//                .signWith(secretKey1) // set Expire Time 해당 옵션 안넣으면 expire안함
//                .compact();
//        System.out.println(token);
//
//        try {
//            Jwts.parser().verifyWith(secretKey1).build().parseSignedClaims(token);
//        } catch (JwtException | IllegalArgumentException e) {
//            throw e;
//        }
//    }

}
