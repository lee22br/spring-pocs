package com.example.scope.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc //Simulate HTTP requests in test
class RequestScopeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateNewBeanInstanceForEachHttpRequest() throws Exception {

        // First HTTP GET request
        MvcResult result1 = mockMvc.perform(get("/api/trace"))
                .andExpect(status().isOk())
                .andReturn();

        String traceIdFromFirstRequest = result1.getResponse().getContentAsString();

        // Second HTTP GET request
        MvcResult result2 = mockMvc.perform(get("/api/trace"))
                .andExpect(status().isOk())
                .andReturn();

        String traceIdFromSecondRequest = result2.getResponse().getContentAsString();

        assertNotNull(traceIdFromFirstRequest);
        assertNotNull(traceIdFromSecondRequest);

        // The IDs must be different because the Bean was destroyed and recreated for the second request!
        assertNotEquals(traceIdFromFirstRequest, traceIdFromSecondRequest,
                "Trace IDs should be different for distinct HTTP requests");
    }
}
