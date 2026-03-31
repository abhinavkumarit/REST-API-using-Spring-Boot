package com.api.book.bootrestbook.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.Entites.book;
import com.api.book.bootrestbook.Repositary.BookRepositary;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookRepositary bookRepo;

    @PostMapping
    public book addBook(@RequestBody book book){
        return bookRepo.save(book);
    }

    @GetMapping
    public List<book> getAllBook(){
        return bookRepo.findAll();
    }
   

}
