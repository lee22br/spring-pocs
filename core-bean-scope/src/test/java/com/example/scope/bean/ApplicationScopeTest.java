package com.example.scope.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationScopeTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldShareStateGloballyAcrossDifferentRequests() throws Exception {
        // 1. Initial global announcement
        mockMvc.perform(get("/api/announcement"))
                .andExpect(content().string("Welcome to our system!"));

        // 2. updates the global announcement
        mockMvc.perform(post("/api/announcement?message=System Maintenance"));

        // 3. New announcement
        mockMvc.perform(get("/api/announcement"))
                .andExpect(content().string("System Maintenance"));
    }
}