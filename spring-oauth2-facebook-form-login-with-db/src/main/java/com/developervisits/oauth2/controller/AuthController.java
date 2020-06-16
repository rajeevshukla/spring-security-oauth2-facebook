package com.developervisits.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.developervisits.oauth2.common.AuthProvider;
import com.developervisits.oauth2.dto.UserDetailsDTO;
import com.developervisits.oauth2.model.RegisterUser;
import com.developervisits.oauth2.service.UserDetailsServiceImpl;

@Controller
public class AuthController {

	@Autowired
	OAuth2AuthorizedClientService authclientService;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@GetMapping("/")
	public String home(Model model, @AuthenticationPrincipal UserDetailsDTO userDetails) {

		
		model.addAttribute("name", userDetails.getFirstName());

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
		UserDetailsDTO user = (UserDetailsDTO) authtoken.getPrincipal();
		System.out.println(user.getFirstName());
		modelAndView.addObject("name", user.getFirstName());
		return modelAndView;
	}

	// Returning the login page.
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
		register(user, AuthProvider.local);
	}

	private void register(RegisterUser user, AuthProvider provider) {
		userDetailsService.registerUser(user,provider);
	}

}
