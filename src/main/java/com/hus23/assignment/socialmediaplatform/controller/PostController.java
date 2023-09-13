package com.hus23.assignment.socialmediaplatform.controller;
import com.hus23.assignment.socialmediaplatform.entities.Post;
import com.hus23.assignment.socialmediaplatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/allposts")
    public ResponseEntity <?> getAllPost()
    {
        return postService.getAllPost();
    }

    @GetMapping("/{location}")
    public ResponseEntity<?> getAllPostFromSpecificLocation(@PathVariable("location") String location)
    {
        return postService.getAllPostFromSpecificLocation(location);
    }
    @PutMapping("/update/{username}/{id}")
    public ResponseEntity <?> updatePostDetailsOfUser(@RequestBody Post post, @PathVariable("username") String username, @PathVariable("id") Long id)
    {
        return postService.updatePostDetailsOfUser(post, username, id);
    }
    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity <?> deleteSinglePostOfUser(@PathVariable("username") String username, @PathVariable("id") Long id)
    {
        return postService.deleteSinglePostOfUser(username, id);
    }
    @DeleteMapping("/delete/{username}")
    public ResponseEntity <?> deleteAllPostsOfUser(@PathVariable("username") String username)
    {
        return postService.deleteAllPostsOfUser(username);
    }
}
