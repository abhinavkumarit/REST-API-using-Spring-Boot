package com.api.book.bootrestbook.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.Entites.User;
import com.api.book.bootrestbook.Services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    private User register(@RequestBody User user){
        return authService.register(user);
    }
    @PostMapping("/create-admin")
    public User creatAdmin(@RequestBody User user){
        return authService.createAdmin(user);
    }
}
