package com.hus23.assignment.socialmediaplatform.service;

import com.hus23.assignment.socialmediaplatform.entities.User;
import com.hus23.assignment.socialmediaplatform.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_WithNonExistingUser_ReturnsUserSavedResponse() {
        User user = new User();
        user.setUsername("vj");
        user.setFirst_name("vaidik");
        user.setLast_name("jhawar");
        user.setBio("No Bio Needed");
        user.setPassword("Password");

        when(userRepository.findById(user.getUsername())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        ResponseEntity<?> response = userService.saveUser(user);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(user, response.getBody());
    }

    @Test
    void saveUser_WithExistingUser_ReturnsUserAlreadyExistResponse() {
        User user = new User();
        user.setUsername("vj");
        user.setFirst_name("vaidik");
        user.setLast_name("jhawar");
        user.setBio("No Bio needed");
        user.setPassword("password");

        when(userRepository.findById(user.getUsername())).thenReturn(Optional.of(user));

        ResponseEntity<?> response = userService.saveUser(user);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("User Already Exist !", response.getBody());
    }

    @Test
    void updateUser_WithNonExistingUser_ReturnsUserDoesNotExistResponse() {
        String username = "vj";
        User user = new User();
        user.setUsername(username);
        user.setFirst_name("vaidik");
        user.setLast_name("jhawar");
        user.setBio("No Bio needed");
        user.setPassword("password");

        when(userRepository.findById(username)).thenReturn(Optional.empty());

        ResponseEntity<?> response = userService.updateUser(user, username);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("User does not exist !", response.getBody());
    }

    @Test
    void updateUser_WithDifferentUsername_ReturnsCannotChangeUsernameResponse() {
        String username = "vj";
        User existingUser = new User();
        existingUser.setUsername(username);
        existingUser.setFirst_name("vaidik");
        existingUser.setLast_name("jhawar");
        existingUser.setBio("No Bio needed");
        existingUser.setPassword("password");

        User updatedUser = new User();
        updatedUser.setUsername("sampleuser");
        updatedUser.setFirst_name("sample");
        updatedUser.setLast_name("user");
        updatedUser.setBio("Sample Bio");
        updatedUser.setPassword("newpassword");

        when(userRepository.findById(username)).thenReturn(Optional.of(existingUser));

        ResponseEntity<?> response = userService.updateUser(updatedUser, username);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("you Can't change Username !", response.getBody());
    }

    @Test
    void deleteUser_WithExistingUser_ReturnsDeletedSuccessfullyResponse() {
        String username = "vj";

        when(userRepository.findById(username)).thenReturn(Optional.of(new User()));

        ResponseEntity<?> response = userService.deleteUser(username);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Deleted Successfully !", response.getBody());
    }

    @Test
    void deleteUser_WithNonExistingUser_ReturnsUserDoesNotExistResponse() {
        String username = "vaidik";

        when(userRepository.findById(username)).thenReturn(Optional.empty());

        ResponseEntity<?> response = userService.deleteUser(username);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("User Does not Exist !", response.getBody());
    }

    @Test
    void getUser_ReturnsListOfAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        when(userRepository.findAll()).thenReturn(userList);

        ResponseEntity<List<User>> response = userService.getUser();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(userList, response.getBody());
    }

    @Test
    void specificUser_WithExistingUser_ReturnsUserResponse() {
        String name = "vaidik";
        User user = new User();
        user.setFirst_name("vaidik");
        user.setLast_name("jhawar");

        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userRepository.findAll()).thenReturn(userList);

        ResponseEntity<?> response = userService.specificUser(name);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(user, response.getBody());
    }

    @Test
    void specificUser_WithNonExistingUser_ReturnsUserDoesNotExistResponse() {
        String name = "vj";

        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = userService.specificUser(name);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("User with this name does not exist !", response.getBody());
    }
}