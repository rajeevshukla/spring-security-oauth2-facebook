package com.developervisits.oauth2.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.developervisits.oauth2.api.Facebook;
import com.developervisits.oauth2.model.ProfileDetails;
import com.developervisits.oauth2.model.RegisterUser;

@Controller
public class AuthController {

	
	@Autowired
	OAuth2AuthorizedClientService authclientService;

	@GetMapping("/") 
	public String home(Model model, @AuthenticationPrincipal OAuth2User oAuth2User) {

		System.out.println(oAuth2User);
		 model.addAttribute("name", oAuth2User.getAttributes().get(("name")));

		return "home";
	}

	@GetMapping("/formLoginSuccess") 
	public ModelAndView formSuccessLogin(@AuthenticationPrincipal UserDetails userDetails) {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("name", userDetails.getUsername());
		return modelAndView;
	}

	@GetMapping("/oauth2LoginSuccess") 
	public ModelAndView oauth2SuccessLogin(@AuthenticationPrincipal OAuth2AuthenticationToken authtoken) { 
		ModelAndView modelAndView = new ModelAndView("home");
		  OAuth2AuthorizedClient client =
		  authclientService.loadAuthorizedClient(authtoken.
		  getAuthorizedClientRegistrationId(), authtoken.getName());
		  System.out.println("Access Token" + client.getAccessToken().getTokenValue());
		 
		modelAndView.addObject("name", authtoken.getPrincipal().getAttributes().get("name"));
        
		Facebook facebook = new Facebook(client.getAccessToken().getTokenValue());
	    ProfileDetails facebookProfile =	facebook.getProfileDetails(); 
		System.out.println("Facebook Profile: "+facebookProfile);
		
         System.out.println( facebook.getProfileDetails() );
		return modelAndView;
	}

	//Returning the login page. 
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("register");
	    mav.addObject("registerUser", new RegisterUser());
		return mav; 
	}
	
	@PostMapping("/register")
	public void registerSuccess(@ModelAttribute RegisterUser user) {
		
	}

}
