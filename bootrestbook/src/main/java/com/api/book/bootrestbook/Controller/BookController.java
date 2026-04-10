package com.api.book.bootrestbook.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.Entites.book;
// import com.api.book.bootrestbook.Repositary.BookRepositary;
import com.api.book.bootrestbook.Services.BookServices;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServices bookService;

    // GET all books
    @GetMapping
    public ResponseEntity<List<book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // GET book by ID
    @GetMapping("/{id}")
    public ResponseEntity<book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // POST - add new book
    @PostMapping
    public ResponseEntity<book> addBook(@RequestBody book book) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(bookService.createBook(book));
    }

    // PUT - update book
    @PutMapping("/{id}")
    public ResponseEntity<book> updateBook(@PathVariable Long id, 
                                           @RequestBody book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    // DELETE - delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
   

}
