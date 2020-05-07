package com.developervisits.oauth2.api;

import com.developervisits.oauth2.model.FacebookProfile;

public class Facebook  extends Api{

	private static final String FACEBOOK_GRAPH_BASE_URL="";
	
	public Facebook(String authToken ) {
		super(authToken);
	}
	
	public FacebookProfile getProfileDetails() {
		
		return null;
	}
}
