package com.lspace.oauth2.facebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lspace.oauth2.facebook.api.Facebook;

@RestController
public class HomeController {

	@Autowired
	Facebook facebook ;
	
	@GetMapping("/")
	public void home() {
		System.out.println(facebook.getProfile());
	}

	
	// when you have multiple provider you use it like this.
	// where oauth2/authorization is just your application's url which is mapped into somewhere in oother ssm url 
	//oauth2/authorization/github
	///oauth2/authorization/google 
}
