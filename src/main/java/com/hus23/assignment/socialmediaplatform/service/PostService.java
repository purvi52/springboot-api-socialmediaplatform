package com.hus23.assignment.socialmediaplatform.service;

import com.hus23.assignment.socialmediaplatform.entities.Post;
import com.hus23.assignment.socialmediaplatform.entities.User;
import com.hus23.assignment.socialmediaplatform.repo.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public ResponseEntity <?> getAllPost()
    {
        List <Post> all_posts = new ArrayList<>();
        for(Post every_post : postRepository.findAll())
        {
            all_posts.add(every_post);
        }
        return new ResponseEntity<>(all_posts, HttpStatus.OK);
    }
    public ResponseEntity<?> getAllPostFromSpecificLocation(String location)
    {
        List <Post> specific_location_posts = new ArrayList<>();
        for(Post every_post : postRepository.findAll())
        {
            if((every_post.getLocation()).equals(location))
                specific_location_posts.add(every_post);
        }
        return new ResponseEntity<>(specific_location_posts, HttpStatus.OK);
    }
    public ResponseEntity <?> updatePostDetailsOfUser(Post new_post, String username, Long id)
    {
        for(Post every_post : postRepository.findAll())
        {
            if(((every_post.getUser()).getUsername()).equals(username) && every_post.getId() == id)
            {
                every_post.setPost_content(new_post.getPost_content());
                every_post.setLocation(new_post.getLocation());
                return new ResponseEntity<>(postRepository.save(every_post), HttpStatus.OK);
            }
        }
        return ResponseEntity.ok("Username Does not Exist !");
    }
    public ResponseEntity <?> deleteSinglePostOfUser(String username, Long id)
    {
        for(Post every_post : postRepository.findAll())
        {
            if(((every_post.getUser()).getUsername()).equals(username) && every_post.getId() == id)
            {
                postRepository.deleteById(id);
                return ResponseEntity.ok("Post Deleted Successfully!");
            }
        }
        return ResponseEntity.ok("Invalid Username or Post Id !");
    }
    public ResponseEntity <?> deleteAllPostsOfUser(String username)
    {
        for(Post every_post : postRepository.findAll())
        {
            if(((every_post.getUser()).getUsername()).equals(username)) {
                postRepository.deleteById(every_post.getId());
            }
        }
        return ResponseEntity.ok("All Posts Deleted of User!");
    }
}
