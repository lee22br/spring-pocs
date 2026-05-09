package com.example.ioc.controller;

import com.example.ioc.model.User;
import com.example.ioc.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Dependency Injection (IoC)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> list() {
        return userService.getAllUsers();
    }
}
