package com.example.authservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.authservice.models.entity.Account;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtil {
    private final String SECRET_KEY = "SecretKey";

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Account account) {
        Algorithm algorithm = getAlgorithm();

        return JWT.create()
                .withSubject(account.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withClaim("role", account.getRoleName())
                .sign(algorithm);
    }
}
