package com.hus23.assignment.socialmediaplatform.repo;

import com.hus23.assignment.socialmediaplatform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
    extends JpaRepository<User, String> {
}
