package com.example.demo.controller;

import com.example.demo.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        // Simulating a database fetch
        Book book = new Book(id, "Effective Java", "Joshua Bloch");
        return ResponseEntity.ok(book);
    }
}
