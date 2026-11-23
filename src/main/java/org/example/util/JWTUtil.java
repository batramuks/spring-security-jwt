package org.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtil {

    private final String key="mysecretkeymysecretkeymysecretkeymysecretkey";
    private final SecretKey secretKey1= Keys.hmacShaKeyFor(key.getBytes());

    public String generateToken(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Token subject is required");
        }

        long EXPIRATION_MILLIS = 1000 * 60 * 60; // 1 hour
        Date expiration = new Date(new Date().getTime() + EXPIRATION_MILLIS);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(secretKey1, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();

    }



    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey1)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid JWT token", exception);
        }
    }

    public boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        return expiration == null || expiration.before(new Date());
    }

    public boolean validateToken(String token, String expectedSubject) {
        if (expectedSubject == null || expectedSubject.isBlank()) {
            return false;
        }
        try {
            Claims claims = extractAllClaims(token);
            return expectedSubject.equals(claims.getSubject())
                    && claims.getExpiration() != null
                    && claims.getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }
}
