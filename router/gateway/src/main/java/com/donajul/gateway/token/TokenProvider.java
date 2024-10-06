package com.donajul.gateway.token;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.Base64;
import java.util.Calendar;
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

        System.out.println("publicKey " + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));

        return Jwts.builder()
                .signWith(keyPair.getPrivate())
                .claim(AUTHORITIES_KEY, "헤헤")
                .claim(AUTHORITIES_KEY + "2", "헤헤")
                .issuedAt(new Date(now))
                // .expiration(validity)

                .expiration(new Date(now))
                .compact();
    }

    public String createRefreshToken() {

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, 14);
        Date expirationDate = calendar.getTime();

        return Jwts.builder()
                .signWith(keyPair.getPrivate())
                .issuedAt(new Date())
                // .expiration(validity)
                .expiration(expirationDate)
                .compact();
    }

    public String recreateToken(Claims claims) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + tokenValidityInMilliseconds);

        return Jwts.builder()
                .signWith(keyPair.getPrivate())
                .claims(claims)
                .expiration(validity)
                .compact();
    }

    public boolean validateRefreshToken(String refreshToken) {
        try {
            Jwts.parser()
                    .verifyWith(keyPair.getPublic())
                    .build()
                    .parseSignedClaims(refreshToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw e;
        }
    }

    // 뭘 return할지 고민..
    public String readingToken(String token, String refreshToken) {

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(keyPair.getPublic())
                    .build()
                    .parseSignedClaims(token);
            System.out.println(claimsJws.toString());
            return token;
        } catch (ExpiredJwtException e) {
            if(validateRefreshToken(refreshToken)) {
                return recreateToken(e.getClaims());
            }

            throw e;
        } catch (JwtException | IllegalArgumentException e) {
            throw e;
        }
    }


    public String getPublicKey() {
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }


    // 토큰으로 클레임을 만들고 이를 이용해 유저 객체를 만들어서 최종적으로 authentication 객체를 리턴
//    public Authentication getAuthentication(String token) {
//        Claims claims = Jwts
//                .parser()w
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
