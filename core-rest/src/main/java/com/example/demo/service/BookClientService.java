package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookClientService {

    private final RestTemplate restTemplate;
    private static final String EXTERNAL_API_URL = "http://api.lnaix.com/v1/books/";

    public BookClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Book fetchExternalBook(String bookId) {
        String url = EXTERNAL_API_URL + bookId;
        // Makes an HTTP GET request and maps the JSON response directly to the Book record
        return restTemplate.getForObject(url, Book.class);
    }
}