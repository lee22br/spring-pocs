package com.example.scope.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class SessionScopeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldKeepStateSessionResetNewSession() throws Exception {
        MockHttpSession sessionUserA = new MockHttpSession();

        mockMvc.perform(post("/api/cart/add?item=Laptop").session(sessionUserA));

        mockMvc.perform(get("/api/cart").session(sessionUserA))
                .andExpect(content().string("[\"Laptop\"]"));

        // Create a NEW Session
        MockHttpSession sessionUserB = new MockHttpSession();

        // It must be empty, because they get a NEW Bean!
        mockMvc.perform(get("/api/cart").session(sessionUserB))
                .andExpect(content().string("[]"));
    }
}
