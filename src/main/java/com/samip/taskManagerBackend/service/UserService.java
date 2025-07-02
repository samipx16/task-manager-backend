package com.samip.taskManagerBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.samip.taskManagerBackend.dao.UserRepo;
import com.samip.taskManagerBackend.model.User;

@Service
public class UserService {
	
@Autowired
	private UserRepo repo;
private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

	public User saveUser(User user) {
		if(repo.findByUsername(user.getUsername()) != null){
			throw new RuntimeException("User Already Exists");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
	return repo.save(user) ;
		
	}
	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}

}
