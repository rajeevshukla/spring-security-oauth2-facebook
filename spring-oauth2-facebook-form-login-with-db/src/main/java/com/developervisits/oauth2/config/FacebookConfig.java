package com.developervisits.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.context.annotation.RequestScope;

import com.developervisits.oauth2.api.Facebook;

@Configuration
public class FacebookConfig {

	@Bean
	@RequestScope
	@Autowired
	public Facebook configureFacebookClient(OAuth2AuthorizedClientService oAuth2ClientService) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String accessToken = null;

		if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
			OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
			if ("facebook".equalsIgnoreCase(authenticationToken.getAuthorizedClientRegistrationId())) {
				OAuth2AuthorizedClient oAuth2AuthorizedClient = oAuth2ClientService.loadAuthorizedClient(
						authenticationToken.getAuthorizedClientRegistrationId(),
						authenticationToken.getPrincipal().getName());
				if (oAuth2AuthorizedClient != null) {
					accessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
				}
			}
		}
		Facebook facebook = new Facebook(accessToken);
		return facebook;
	}

}
