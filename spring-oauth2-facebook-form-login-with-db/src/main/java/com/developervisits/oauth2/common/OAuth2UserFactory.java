package com.developervisits.oauth2.common;

import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuth2UserFactory {

	public static OAuth2User getOAuth2User(String registrationId, Map<String, Object> attributes) {
		if(registrationId.equals(OAuth2Provider.facebook)) {
			
			return null;
		} else if (registrationId.equalsIgnoreCase(OAuth2Provider.google.name())) {
			
			return null;
		} else {
			throw new RuntimeException("Login with "+registrationId +" not supported");
		}
	}
}
