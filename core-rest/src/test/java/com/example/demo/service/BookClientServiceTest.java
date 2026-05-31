package com.example.demo.service;

import com.example.demo.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookClientServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BookClientService bookClientService;

    @Test
    void fetchExternalBook_ShouldReturnBookSuccessfully() {
        String bookId = "999";
        String expectedUrl = "http://api.lnaix.com/v1/books/999";
        Book mockResponse = new Book("999", "Clean Code", "Robert C. Martin");

        when(restTemplate.getForObject(eq(expectedUrl), eq(Book.class)))
                .thenReturn(mockResponse);
        Book result = bookClientService.fetchExternalBook(bookId);

        assertNotNull(result);
        assertEquals("999", result.id());
        assertEquals("Clean Code", result.title());
        assertEquals("Robert C. Martin", result.author());

        // Verify that one call
        verify(restTemplate).getForObject(expectedUrl, Book.class);
    }
}
