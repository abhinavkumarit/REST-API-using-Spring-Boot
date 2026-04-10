package com.api.book.bootrestbook.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.book.bootrestbook.Entites.Role;
import com.api.book.bootrestbook.Entites.User;
import com.api.book.bootrestbook.Repositary.UserRepositary;

@Service
public class AuthService {

    @Autowired
    private UserRepositary userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        return userRepo.save(user);
    }

    public User createAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        return userRepo.save(user);
    }
    
}