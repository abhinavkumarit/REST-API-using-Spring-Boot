package com.api.book.bootrestbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.book.bootrestbook.Entites.User;
import com.api.book.bootrestbook.Repositary.UserRepositary;

@Service
public class CustomUserDetailService implements UserDetailsService{
    @Autowired
    private UserRepositary userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }

}
