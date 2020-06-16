package com.developervisits.oauth2.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

import com.developervisits.oauth2.api.Facebook;
import com.developervisits.oauth2.model.FacebookProfile;
import com.developervisits.oauth2.model.FacebookUser;
import com.developervisits.oauth2.model.OAuth2UserInfo;

public class OAuth2UserFactory {

	public static OAuth2UserInfo getOAuth2User(OAuth2UserRequest request) {
		if(AuthProvider.facebook.name().equalsIgnoreCase(request.getClientRegistration().getRegistrationId())) {
			Facebook facebook = new Facebook(request.getAccessToken().getTokenValue());
			FacebookProfile profile = facebook.getProfileDetails();
            System.out.println(profile);
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("id", profile.getId());
			attributes.put("name", profile.getName());
			attributes.put("first_name", profile.getFirst_name());
			attributes.put("last_name", profile.getLast_name());
			attributes.put("email", profile.getEmail());
			
			FacebookUser facebookUser = new FacebookUser(attributes);
			return facebookUser;
			
		}  else {
			throw new RuntimeException("Login with "+ request.getClientRegistration().getRegistrationId() +" not supported");
		}
	}
	
	
}
