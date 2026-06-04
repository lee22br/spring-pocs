package com.example.demo.controller;

import com.example.demo.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }
}
