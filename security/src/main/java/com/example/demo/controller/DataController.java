package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @PostMapping("/update")
    public String updateData() {
        return "Data updated successfully!";
    }
}