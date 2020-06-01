package com.developervisits.oauth2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.developervisits.oauth2.common.AuthProvider;
import com.developervisits.oauth2.model.RegisterUser;
import com.developervisits.oauth2.service.UserDetailsServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	UserDetailsServiceImpl userService; 
	
	@PostMapping("/registerUser") 
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUser registerUser) {
		userService.registerUser(registerUser,AuthProvider.local);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
