package com.api.book.bootrestbook.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.Entites.book;

@RestController
public class BookController {
    
    @GetMapping("/book")
    public book getBooks(){
        // return "This is the first book";
        book bk=new book();
        bk.setId(101);
        bk.setTitle("Ziza zoo");
        bk.setAuthor("Adity Yadav");
        return bk;
    }
}
