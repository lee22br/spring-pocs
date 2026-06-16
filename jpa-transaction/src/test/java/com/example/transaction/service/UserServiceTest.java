package com.example.transaction.service;

import com.example.transaction.domain.User;
import com.example.transaction.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUserSuccessfully() {
        User user = new User();
        user.setName("leeiglezias");

        userService.createUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldThrowExceptionWhenUserIsNull() {
        User user = new User();

        user.setName(null);

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }
}
