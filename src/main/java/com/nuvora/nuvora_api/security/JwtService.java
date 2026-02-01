package com.nuvora.nuvora_api.security;

import com.nuvora.nuvora_api.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationInMs;

    private SecretKey getSigningKey() {
       return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("orgId", user.getOrgId().toString())
                .claim("role", user.getRole())
                .claim("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(getSigningKey())
                .compact();
    }

    public UUID extractUserId(String token) {
        String subject = extractAllClaims(token).getSubject();
        return UUID.fromString(subject);
    }

    public UUID extractOrgId(String token) {
        String orgId = extractAllClaims(token).get("orgId",  String.class);
        return UUID.fromString(orgId);
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role",  String.class);
    }

    public boolean isTokenValid(String token) {
        return !
                extractAllClaims(token).getExpiration().before(
                        new Date());
    }

    private Claims  extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
