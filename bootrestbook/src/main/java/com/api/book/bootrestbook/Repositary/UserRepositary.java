package com.api.book.bootrestbook.Repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.book.bootrestbook.Entites.User;

public interface UserRepositary extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
