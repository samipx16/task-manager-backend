package com.samip.task_manager_backend.dao;

import com.samip.task_manager_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    User findByUsername(String username);
    // JPA Repository provides built-in methods for CRUD operations


}
