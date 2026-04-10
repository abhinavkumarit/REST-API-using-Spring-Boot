package com.api.book.bootrestbook.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.bootrestbook.Entites.book;
import com.api.book.bootrestbook.Repositary.BookRepositary;

@Service
public class BookServices {

    @Autowired
    private BookRepositary bookRepository;

    // Get all books
    public List<book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get book by ID
    public book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // Create a new book
    public book createBook(book book) {
        return bookRepository.save(book);
    }

    // Update existing book
    public book updateBook(Long id, book updatedBook) {
        book existing = getBookById(id);
        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setPrice(updatedBook.getPrice());
        existing.setStock(updatedBook.getStock());
        return bookRepository.save(existing);
    }

    // Delete a book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    
}