package com.developervisits.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

	@Autowired
	OAuth2AuthorizedClientService authclientService;

	@RequestMapping("login")
	public String login() {
		System.out.println("In side login controller ");
		return "login";
	}

	@RequestMapping("/oauth2LoginSuccess")
	public String getOauth2LoginInfo(Model model,@AuthenticationPrincipal OAuth2AuthenticationToken authenticationToken) {
		// fetching the client details and user details and then adding them into 
		System.out.println(authenticationToken.getAuthorizedClientRegistrationId()); // client name like facebook, google etc.
		System.out.println(authenticationToken.getName()); // facebook/google userId
		
		//	1.Fetching User Info
		OAuth2User user = authenticationToken.getPrincipal(); // When you work with Spring OAuth it gives you OAuth2User instead of UserDetails
		System.out.println("userId: "+user.getName()); // returns the userId of facebook
		// getAttributes map Contains User details like name, email etc// print the whole map for more details
		System.out.println("Email:"+ user.getAttributes().get("email"));
        
		//2. Just in case if you want to obtain User's auth token value, refresh token, expiry date etc you can use below snippet
        OAuth2AuthorizedClient client = authclientService.loadAuthorizedClient(authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getName());
        System.out.println("Token Value"+ client.getAccessToken().getTokenValue()); 
		
		//3. Now you have full control on users data.You can either see if user is not present in Database then store it and 
        // send welcome email for the first time 
        model.addAttribute("name", user.getAttribute("name"));

        return "home";
	}

	@RequestMapping("/formLoginSuccess")
	public String getFormLoginInfo(Model model, @AuthenticationPrincipal Authentication authentication) {
        // In form-based login you get UserDetails as principal
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		model.addAttribute("name", userDetails.getUsername());
		return "home";

	}
	
	@GetMapping(value = {"/"}) 
	public String home(Model model, @AuthenticationPrincipal Authentication authentication) {
        System.out.println("Request appeared on server.");
		// authentication's principle could be either through OAuth or via form-based so you have to cast the principle object into User object carefully. 
		if(authentication.getPrincipal() instanceof UserDetails) {
			System.out.println("It was a form based login");
			UserDetails user = (UserDetails) authentication.getPrincipal();
			model.addAttribute("name", user.getUsername());
			
		} else  {
			System.out.println("It was OAuth based login");
			OAuth2User user = (OAuth2User) authentication.getPrincipal();
			model.addAttribute("name", user.getAttribute("name"));
		}
				
		return "home";
	}


}
