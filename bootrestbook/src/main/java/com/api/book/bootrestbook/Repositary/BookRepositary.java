package com.api.book.bootrestbook.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.book.bootrestbook.Entites.book;

public interface BookRepositary extends JpaRepository<book, Long>{
    
}
