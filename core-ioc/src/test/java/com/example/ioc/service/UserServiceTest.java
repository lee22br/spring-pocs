package com.example.ioc.service;

import com.example.ioc.model.User;
import com.example.ioc.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService; //Inject mock

    @Test
    void shouldCreateUserSuccessfully() {
        User newUser = new User("John Doe", "john@email.com");
        User savedUser = new User("John Doe", "john@email.com");
        savedUser.setId(1L);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.createUser(newUser);

        assertNotNull(result.getId()); // must be new ID
        assertEquals("John Doe", result.getName());

        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void shouldReturnAllUsers() {
        User user1 = new User("John", "john@email.com");
        User user2 = new User("Jane", "jane@email.com");
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }
}