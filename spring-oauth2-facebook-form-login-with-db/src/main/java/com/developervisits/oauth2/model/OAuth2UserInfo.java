package com.developervisits.oauth2.model;

import java.util.Map;

import com.developervisits.oauth2.common.AuthProvider;

public abstract class OAuth2UserInfo {

	protected Map<String, Object> attributes;

	public OAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public abstract String getEmail();
	public abstract String getFirstName();
	public abstract String getLastName();
	public abstract String getId();
	public abstract AuthProvider getProvider();
	

}
