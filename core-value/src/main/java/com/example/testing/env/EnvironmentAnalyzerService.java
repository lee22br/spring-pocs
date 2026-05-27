package com.example.testing.env;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EnvironmentAnalyzerService {

    private final Environment environment;

    public EnvironmentAnalyzerService(Environment environment) {
        this.environment = environment;
    }

    public boolean isProductionProfileActive() {
        // Check if "prod" env is active
        String[] activeProfiles = environment.getActiveProfiles();
        return Arrays.asList(activeProfiles).contains("prod");
    }

    public String getApplicationName() {
        return environment.getProperty("spring.application.name", "Unknown App");
    }
}
