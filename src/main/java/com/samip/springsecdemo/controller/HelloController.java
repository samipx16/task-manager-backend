package com.samip.springsecdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

	@GetMapping("hello")
	public String greet(HttpServletRequest request) {
		return "Hello World ";
	}
	
	@GetMapping("about")
	public String about(HttpServletRequest request) {
		return "Samip";
	}
}
