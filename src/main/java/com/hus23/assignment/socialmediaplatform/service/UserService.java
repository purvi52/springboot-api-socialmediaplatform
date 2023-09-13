package com.hus23.assignment.socialmediaplatform.service;

import com.hus23.assignment.socialmediaplatform.entities.Post;
import com.hus23.assignment.socialmediaplatform.entities.User;
import com.hus23.assignment.socialmediaplatform.repo.PostRepository;
import com.hus23.assignment.socialmediaplatform.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    public ResponseEntity<?> saveUser(User user)
    {
        if((userRepository.findById(user.getUsername())).isEmpty()) {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        }
        return ResponseEntity.ok("User Already Exist !");
    }

    public ResponseEntity<?> updateUser(User user, String username)
    {
        if((userRepository.findById(username)).isEmpty()){
            return ResponseEntity.ok("User does not exist !");
        } else if ((user.getUsername().equals(username)) != true) {
            return ResponseEntity.ok("you Can't change Username !");
        }
        User userData = (userRepository.findById(username)).get();
        userData.setFirst_name(user.getFirst_name());
        userData.setLast_name(user.getLast_name());
        userData.setBio(user.getBio());
        userData.setPassword(user.getPassword());
        return new ResponseEntity<>(userRepository.save(userData), HttpStatus.OK);
    }
    public ResponseEntity<?> deleteUser(String username)
    {
        if(!(userRepository.findById(username)).isEmpty())
        {
            userRepository.deleteById(username);
            return ResponseEntity.ok("Deleted Successfully !");
        }
        else {
            return ResponseEntity.ok("User Does not Exist !");
        }
    }

    public ResponseEntity <List <User>> getUser()
    {
        List <User> all_users = new ArrayList<>();
        userRepository.findAll().forEach(all_users::add);
        return new ResponseEntity<>(all_users, HttpStatus.OK);
    }

    public ResponseEntity <?> specificUser(String name)
    {
        for(User user : userRepository.findAll())
        {
            if((user.getFirst_name()).equals(name) | (user.getLast_name()).equals(name)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            String display_name = user.getFirst_name().concat(user.getLast_name());
            if((display_name.equals(name)))
                return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return ResponseEntity.ok("User with this name does not exist !");
    }

    public ResponseEntity <?> addPostToUser(Post post, String username)
    {
        if((userRepository.findById(username)).isEmpty()) {
            return ResponseEntity.ok("User Does not exist");
        }
        post.setUser(userRepository.findById(username).get());
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
    }
}
