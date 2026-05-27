package com.example.testing.env;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = "spring.application.name=LNAIX-App")
@ActiveProfiles("prod") //set profile to prod
class EnvironmentAnalyzerServiceTest {

    @Autowired
    private EnvironmentAnalyzerService analyzerService;

    @Test
    void shouldDetectProductionProfile() {

        assertTrue(analyzerService.isProductionProfileActive());
    }

    @Test
    void shouldFetchInjectedPropertyName() {
        assertEquals("LNAIX-App", analyzerService.getApplicationName());
    }
}
