package com.example.transaction.service;

import com.example.transaction.domain.User;
import com.example.transaction.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(User user) {
        userRepository.save(user);

        // If an error happens here, the save above is reverted
        if (null == user.getName()) {
            throw new RuntimeException("User name cannot be null");
        }
    }

    @Transactional
    public void registerUserWithError(User user) {
        userRepository.save(user);
        throw new RuntimeException("Exception to Test Rollback!");
    }
}
