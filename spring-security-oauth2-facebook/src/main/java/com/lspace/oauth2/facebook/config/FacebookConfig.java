package com.lspace.oauth2.facebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.context.annotation.RequestScope;

import com.lspace.oauth2.facebook.api.Facebook;

@Configuration
public class FacebookConfig {

	@Bean
	@RequestScope
	public Facebook facebook(OAuth2AuthorizedClientService clientService) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String accessToken = null;
		if(authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
			OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
			String clientRegistrationId = auth2AuthenticationToken.getAuthorizedClientRegistrationId();
			if(clientRegistrationId.equals("facebook")) { 
				OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId,auth2AuthenticationToken.getName());
				if(client != null)
				accessToken = client.getAccessToken().getTokenValue();  // obtaining the token after authentication
				System.out.println("Access Token:"+ accessToken);
			}
		}
		return new Facebook(accessToken);
	}
	
	
}
