package com.api.book.bootrestbook.config;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
@Component
public class jwtUtil {

    private final String SECRET = "mysecretkeymysecretkeymysecretkey123456";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ Generate token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    // ✅ Extract email from token
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ Validate token against user details
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String email = extractEmail(token);
            return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired: " + e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid token: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Token error: " + e.getMessage());
            return false;
        }
    }

    // ✅ Check if token is expired
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // ✅ Extract all claims safely
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}