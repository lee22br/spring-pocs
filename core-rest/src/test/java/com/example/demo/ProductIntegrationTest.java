package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Starts the full application context on a completely random available local port
@SpringBootTest(classes = BookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductService productService;


    @Test
    void shouldReturnProductDetails() {
        String url = "http://localhost:" + port + "/api/products/1";

        //ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
        Product respBook = this.restTemplate.getForObject(url, Product.class);


//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        Product retrievedProduct = response.getBody();
        assertNotNull(respBook);
    }

    @Test
    void shouldCreateAndThenRetrieveProductSuccessfully() {

        Product requestPayload = new Product(null, "Keyboard", 149.90);

        ResponseEntity<Product> postResponse = restTemplate.postForEntity(
                "/api/products",
                requestPayload,
                Product.class
        );

        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        Product createdProduct = postResponse.getBody();
        assertNotNull(createdProduct);
        assertNotNull(createdProduct.id());
        assertEquals("Keyboard", createdProduct.name());
        assertEquals(149.90, createdProduct.price());

        // --- STEP 2: RETRIEVE (GET) ---
        Long productId = createdProduct.id();

        // Act: Sending a real HTTP GET request to verify the state was persisted
        ResponseEntity<Product> getResponse = restTemplate.getForEntity(
                "/api/products/" + productId,
                Product.class
        );

        // Assert GET execution
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        Product retrievedProduct = getResponse.getBody();
        assertNotNull(retrievedProduct);
        assertEquals(productId, retrievedProduct.id());
        assertEquals("Developer Keyboard", retrievedProduct.name());
    }
}
