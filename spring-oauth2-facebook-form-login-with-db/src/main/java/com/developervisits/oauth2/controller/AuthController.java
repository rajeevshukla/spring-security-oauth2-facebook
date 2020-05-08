package com.developervisits.oauth2.controller;

import javax.annotation.PostConstruct;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	
	
	@GetMapping("/") 
	public String home(Model medel, @AuthenticationPrincipal OAuth2User oAuth2User) {
		 
		System.out.println();
		
		return "home";
	}
	
	@GetMapping("/formSuccessLogin") 
	public void formSuccessLogin() {
		
	}
	
	@GetMapping("/oauth2SuccesLogin") 
	public void oauth2SuccessLogin() { 
		
	}
	
	@GetMapping("/login")
	public String login() {
     		return "login";
	}
  
}
