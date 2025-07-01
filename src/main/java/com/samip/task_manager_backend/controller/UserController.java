package com.samip.task_manager_backend.controller;


import com.samip.task_manager_backend.model.User;
import com.samip.task_manager_backend.service.JwtService;
import com.samip.task_manager_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;
	
	@PostMapping("register")
	public User register(@RequestBody User user) {
	  return service.registerUser(user);
	}

	@PostMapping("login")
	public String login(@RequestBody User user){
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
		if(authentication.isAuthenticated())
			return jwtService.generateToken(user.getEmail());
		else
			return"login failed";
	}
}
