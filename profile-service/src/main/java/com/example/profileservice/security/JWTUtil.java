package com.example.profileservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTUtil {
    @Value("${jwt-secret}")
    private String secretKey;

    public Long validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                .withSubject("Account details")
                .withIssuer("auth-service")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("id").asLong();
    }
}
