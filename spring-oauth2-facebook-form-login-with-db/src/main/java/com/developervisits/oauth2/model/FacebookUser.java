package com.developervisits.oauth2.model;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class FacebookUser implements OAuth2User { 
	
	private Map<String, Object> attributes;
	private Collection<? extends GrantedAuthority> authorities;
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String name;
	
	
	public FacebookUser(Map<String, Object> attributes) {
		 this.attributes = attributes;
		 this.id = (String)attributes.get("id");
		 this.firstName = (String)attributes.get("firstName");
		 this.lastName = (String)attributes.get("lastName");
		 this.email = (String)attributes.get("email");
		 this.authorities = (Collection<GrantedAuthority>)attributes.get("authorities");
	}

}
