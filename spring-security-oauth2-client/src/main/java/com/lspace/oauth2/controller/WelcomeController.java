package com.lspace.oauth2.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	
	
	@GetMapping("/welcome") 
	public String home(Model model,  @AuthenticationPrincipal OAuth2AuthenticationToken token) {
		
		
		model.addAttribute("name", token.getPrincipal().getAttribute("name"));
		
		return "home";
	}
	
}
