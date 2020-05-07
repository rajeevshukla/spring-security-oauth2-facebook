package com.developervisits.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	
	
	@GetMapping("/") 
	public void home() { 
		
	}
	
	@GetMapping("/formSuccessLogin") 
	public void formSuccessLogin() {
		
	}
	
	@GetMapping("/oauth2SuccesLogin") 
	public void oauth2SuccessLogin() { 
		
	}
	
	
  
}
