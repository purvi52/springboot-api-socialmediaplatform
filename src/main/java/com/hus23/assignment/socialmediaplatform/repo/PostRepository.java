package com.hus23.assignment.socialmediaplatform.repo;

import com.hus23.assignment.socialmediaplatform.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
