package com.lspace.oauth2.facebook.api;

public class Facebook  extends ApiBinding{

	private static final String GRAPH_API_BASE_URL ="https://graph.facebook.com/v2.12";
	
	public Facebook(String accessToken) {
		super(accessToken);
	}
	
	public ProfileDetails getProfile() {
		return restTemplate.getForObject(GRAPH_API_BASE_URL+"/me?fields=id,email,last_name,first_name", ProfileDetails.class);
	}
	
}
