package com.developervisits.oauth2.model;

import java.util.Map;

import com.developervisits.oauth2.common.AuthProvider;

public class FacebookUser extends OAuth2UserInfo { 
	public FacebookUser(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getEmail() {
		return (String)attributes.get("email");
	}


	@Override
	public String getFirstName() {
		return (String)attributes.get("first_name");
	}


	@Override
	public String getLastName() {
		return (String)attributes.get("last_name");
	}

	@Override
	public String getId() {
		return (String)attributes.get("id");
	}
	
	@Override
	public AuthProvider getProvider() {
		return AuthProvider.facebook;
	}


}
