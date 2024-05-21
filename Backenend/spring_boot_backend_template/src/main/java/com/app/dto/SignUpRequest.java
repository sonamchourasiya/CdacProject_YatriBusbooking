package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class SignUpRequest {
	
	private String  firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;

}
