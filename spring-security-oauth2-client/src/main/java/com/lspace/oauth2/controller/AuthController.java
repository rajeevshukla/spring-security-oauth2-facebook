package com.lspace.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String getOauth2LoginInfo(Model model, OAuth2AuthenticationToken authenticationToken) {

		// fetching the client details and user details and then adding them into 

		System.out.println(authenticationToken.getAuthorizedClientRegistrationId()); // client name like facebook, google etc.
		System.out.println(authenticationToken.getName()); // facebook userId

		OAuth2AuthorizedClient client = 	authclientService.loadAuthorizedClient(authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getName());

		OAuth2User user = authenticationToken.getPrincipal();
		System.out.println("user name"+user.getName());
		System.out.println(user.getAttributes());

		// headers.add(HttpHeaders.AUTHORIZATION, "Bearer"+client.getAccessToken().getTokenValue());

		// Now here check if user is already present in your database and send welcome email if not. 

		return "redirect:/welcome";
	}

	@RequestMapping("/formLoginSuccess")
	public String getFormLoginInfo(Model model, @AuthenticationPrincipal UserDetails user) {

		model.addAttribute("name", user.getUsername());
		return "home";

	}
	@GetMapping("/welcome") 
	public String home(Model model,  @AuthenticationPrincipal OAuth2AuthenticationToken token) {


		model.addAttribute("name", token.getPrincipal().getAttribute("name"));

		return "home";
	}


}
