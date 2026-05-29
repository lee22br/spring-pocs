package com.example.testing.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class HealthCheckController {

    @GetMapping("/ping")
    public Map<String, String> ping() {
        // Response simple Json: {"status": "UP", "message": "Pong!"}
        return Map.of(
                "status", "UP",
                "message", "Pong!"
        );
    }
}
