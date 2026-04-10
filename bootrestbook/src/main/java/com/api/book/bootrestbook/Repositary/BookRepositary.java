package com.api.book.bootrestbook.Repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.book.bootrestbook.Entites.book;

public interface BookRepositary extends JpaRepository<book, Long>{
    List<book> findByAuthor(String author);
    List<book> findByTitle(String title);
}
