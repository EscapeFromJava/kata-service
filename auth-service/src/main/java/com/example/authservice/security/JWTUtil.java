package com.example.authservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.authservice.models.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTUtil {
    @Value("${jwt-secret}")
    private String secretKey;

    public String generateAccessToken(Account account) {
        return JWT.create()
                .withSubject("Account details")
                .withClaim("id", account.getId())
                .withClaim("email", account.getEmail())
                .withClaim("role", account.getRoleName())
                .withIssuer("auth-service")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .sign(Algorithm.HMAC256(secretKey));
    }
}
