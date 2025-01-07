package com.zara.zaraservice.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.zara.zaraservice.constants.Constant.*;

@Component
public class JwtService {

    // Generate Access Token
    public String generateToken(String username, String firstname, String lastname, String email, String avatar, Long id, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("firstname", firstname)
                .claim("lastname", lastname)
                .claim("email", email)
                .claim("avatar", avatar)
                .claim("id", id)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // Generate Refresh Token
    public String generateRefreshToken(String username, String firstname, String lastname, String email, String avatar, Long id, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("firstname", firstname)
                .claim("lastname", lastname)
                .claim("email", email)
                .claim("avatar", avatar)
                .claim("id", id)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username from token
    public String extractUsername(String token) {
        try {
            return parseToken(token).getBody().getSubject();
        } catch (JwtException e) {
            System.err.println("Failed to extract username from token: " + e.getMessage());
            return null;
        }
    }

    // Validate Token
    public boolean validateToken(String token, String username) {
        try {
            Jws<Claims> claims = parseToken(token);
            String tokenUsername = claims.getBody().getSubject();
            return username.equals(tokenUsername) && !isTokenExpired(claims);
        } catch (JwtException e) {
            System.err.println("Token validation error: " + e.getMessage());
            return false;
        }
    }

    // Parse Token
    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
    }

    // Check if token is expired
    private boolean isTokenExpired(Jws<Claims> claims) {
        return claims.getBody().getExpiration().before(new Date());
    }
}