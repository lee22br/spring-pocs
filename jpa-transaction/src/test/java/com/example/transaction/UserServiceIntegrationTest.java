package com.example.transaction;

import com.example.transaction.domain.User;
import com.example.transaction.repository.UserRepository;
import com.example.transaction.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testTransactionRollback() {
        User user = new User();
        user.setName("leeiglezias");

        assertThrows(RuntimeException.class, () -> {
            userService.registerUserWithError(user);
        });

        assertEquals(0, userRepository.count(), "DB should be empty because the rollback!");
    }

    @Test
    void testTransaction() {
        User user = new User();
        user.setName("leeiglezias");

        userService.createUser(user);

        assertEquals(1, userRepository.count(), "DB should be empty because the rollback!");
    }
}
