package com.developervisits.oauth2.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FacebookProfile{

	private String id;
	private String name;
	private String email;
	private String first_name;
	private String last_name;
}
