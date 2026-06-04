package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    private final RestTemplate restTemplate;

    private static final String EXTERNAL_API_URL = "http://api.lnaix.com/v1/products/";

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product fetchExternalBook(String productId) {
        String url = EXTERNAL_API_URL + productId;
        // Makes an HTTP GET request and maps the JSON response directly to the Book record
        return restTemplate.getForObject(url, Product.class);
    }
}
