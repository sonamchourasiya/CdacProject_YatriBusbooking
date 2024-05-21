package com.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
private int userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	//skips the field/property during serialization
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String phoneNumber;


	

	
	
	public UserDto(String firstName, String lastName, String email, String password, String phoneNumber
			) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		
	}
}
