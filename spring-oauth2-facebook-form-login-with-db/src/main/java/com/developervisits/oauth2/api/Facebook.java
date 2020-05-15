package com.developervisits.oauth2.api;

import com.developervisits.oauth2.model.ProfileDetails;

public class Facebook  extends ApiBinding {

	private static final String FACEBOOK_GRAPH_BASE_URL="https://graph.facebook.com/v7.0";
	
	public Facebook(String authToken) {
		super(authToken);
	}

	public ProfileDetails getProfileDetails() {
		System.out.println("Fetching facebook profile details");
		return restTemplate.getForObject(FACEBOOK_GRAPH_BASE_URL+"/me?fields=id,email,last_name,first_name", ProfileDetails.class);
	}
	
}
