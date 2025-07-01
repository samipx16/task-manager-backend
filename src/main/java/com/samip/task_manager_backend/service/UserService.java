package com.samip.task_manager_backend.service;

import com.samip.task_manager_backend.dao.UserDao;
import com.samip.task_manager_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User registerUser(User user) {
        userDao.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED).getBody();
    }

    public ResponseEntity<String> loginUser(String email, String password) {
        Optional<User> userOpt = userDao.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) { // plaintext comparison for now
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


}
