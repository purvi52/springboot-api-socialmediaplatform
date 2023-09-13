package com.hus23.assignment.socialmediaplatform.controller;

import com.hus23.assignment.socialmediaplatform.entities.Post;
import com.hus23.assignment.socialmediaplatform.entities.User;
import com.hus23.assignment.socialmediaplatform.service.PostService;
import com.hus23.assignment.socialmediaplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody User user)
    {
        return userService.saveUser(user);
    }

    @PutMapping("/update/{username}")

    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("username") String username)
    {
        return userService.updateUser(user, username);
    }

    @DeleteMapping("delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username)
    {
        return userService.deleteUser(username);
    }
    @GetMapping("/users")
    public ResponseEntity <List <User>> getUser()
    {
        return userService.getUser();
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<?> specificUser(@PathVariable("name") String name)
    {
        return userService.specificUser(name);
    }

    @PostMapping("/addpost/{username}")
    public ResponseEntity <?> addPostToUser(@RequestBody Post post, @PathVariable String username)
    {
        return userService.addPostToUser(post, username);
    }
}
