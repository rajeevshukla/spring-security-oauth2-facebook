package com.developervisits.oauth2.common;

import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.developervisits.oauth2.model.FacebookUser;

public class OAuth2UserFactory {

	public static OAuth2User getOAuth2User(String registrationId, Map<String, Object> attributes) {
		if(registrationId.equals(AuthProvider.facebook.toString())) {
			return new FacebookUser(attributes);
		}  else {
			throw new RuntimeException("Login with "+registrationId +" not supported");
		}
	}
}
