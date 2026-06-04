package com.example.demo;

import com.example.demo.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class BookControllerIntegrationTest {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void shouldCreateAndRetrieveBook() {
        Book newBook = new Book("1", "The Pragmatic Programmer", "Andy Hunt");

        restTestClient.post()
                .uri("/api/books")
                .body(newBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(book -> {
                    assertThat(book.id()).isEqualTo("1");
                    assertThat(book.title()).isEqualTo("The Pragmatic Programmer");
                    assertThat(book.author()).isEqualTo("Andy Hunt");
                });

        restTestClient.get()
                .uri("/api/books")
                .exchange()
                .expectStatus().isOk();

    }
}