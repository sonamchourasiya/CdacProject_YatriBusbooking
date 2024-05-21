package com.app.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SigninResponse {
	private Long id;
	private String firstName;
	
	private String lastName;
	private String email;
	
	private String password;
	private String phoneNumber;
	private String role;
}
