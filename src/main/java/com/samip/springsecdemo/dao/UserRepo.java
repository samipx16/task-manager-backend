package com.samip.springsecdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samip.springsecdemo.model.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
