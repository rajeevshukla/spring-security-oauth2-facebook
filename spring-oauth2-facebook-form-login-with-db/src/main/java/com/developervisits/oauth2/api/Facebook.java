package com.developervisits.oauth2.api;

import com.developervisits.oauth2.model.FacebookProfile;

public class Facebook  extends ApiBinding {

	private static final String FACEBOOK_GRAPH_BASE_URL="https://graph.facebook.com/v6.0";
	
	public Facebook(String authToken) {
		super(authToken);
	}

	public FacebookProfile getProfileDetails() {
		return restTemplate.getForObject(FACEBOOK_GRAPH_BASE_URL+"/me?fields=id,email,last_name,first_name", FacebookProfile.class);
	}
	
}
