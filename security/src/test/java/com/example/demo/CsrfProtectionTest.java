package com.example.demo;

import com.example.demo.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CsrfProtectionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void whenPostWithoutCsrf_thenForbidden() throws Exception {
        mockMvc.perform(post("/update"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void whenPostWithCsrf_thenOk() throws Exception {
        mockMvc.perform(post("/update").with(csrf()))
                .andExpect(status().isOk());
    }
}
