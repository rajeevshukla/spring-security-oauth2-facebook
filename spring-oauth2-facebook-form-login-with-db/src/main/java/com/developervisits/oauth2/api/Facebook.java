package com.developervisits.oauth2.api;

import com.developervisits.oauth2.model.FacebookProfile;

public class Facebook  extends ApiBinding {

	private static final String FACEBOOK_GRAPH_BASE_URL="https://graph.facebook.com";
	
	public Facebook(String authToken) {
		super(authToken);
	}

	public FacebookProfile getProfileDetails() {
		return restTemplate.getForObject(FACEBOOK_GRAPH_BASE_URL+"/me?name,email,first_name,last_name", FacebookProfile.class);
	}
	
}
