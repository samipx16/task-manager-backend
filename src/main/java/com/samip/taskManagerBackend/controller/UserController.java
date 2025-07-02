package com.samip.taskManagerBackend.controller;

import com.samip.taskManagerBackend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.samip.taskManagerBackend.model.User;
import com.samip.taskManagerBackend.service.UserService;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;
	
	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody User user) {
		try{
			User savedUser = service.saveUser(user);
			return ResponseEntity.ok("User registered successfully");
		}catch  (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("login")
	public String login(@RequestBody User user){
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		if(authentication.isAuthenticated())
			return jwtService.generateToken(user.getUsername());
		else
			return"login failed";
	}
}
