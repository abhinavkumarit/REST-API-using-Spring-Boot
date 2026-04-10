package com.api.book.bootrestbook.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.Entites.User;
import com.api.book.bootrestbook.Services.AuthService;
import com.api.book.bootrestbook.config.jwtUtil;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private jwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.get("email"),
                    request.get("password")
                )
            );
            String token = jwtUtil.generateToken(request.get("email"));
            return ResponseEntity.ok(Map.of("token", token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(Map.of("error", "Invalid email or password"));
        }
    }

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User savedUser = authService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(Map.of("message", "User registered successfully",
                                              "email", savedUser.getEmail()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("error", e.getMessage()));
        }
    }

    // Create Admin endpoint (protected - only existing admin can call)
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        try {
            User savedUser = authService.createAdmin(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(Map.of("message", "Admin created successfully",
                                              "email", savedUser.getEmail()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("error", e.getMessage()));
        }
    }
}