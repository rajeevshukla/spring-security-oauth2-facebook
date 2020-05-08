package com.developervisits.oauth2.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterUser {

	private String username;
	private String password;
	private String emailId;
	private String firstName;
	private String lastName;
	
}
