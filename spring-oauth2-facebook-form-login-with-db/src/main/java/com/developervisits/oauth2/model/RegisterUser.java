package com.developervisits.oauth2.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class RegisterUser {

	private String username;
	private String password;
	private String repassword;
	private String email;
	private String firstName;
	private String lastName;
	
}
