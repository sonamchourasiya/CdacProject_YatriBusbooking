package com.app.service;

import javax.validation.Valid;

import com.app.dto.ApiResponse;
import com.app.dto.SignUpRequest;
import com.app.dto.SignUpResponse;
import com.app.dto.SigninRequest;
import com.app.dto.SigninResponse;
import com.app.entities.User;

public interface UserService {

	SigninResponse userSignIn(SigninRequest reqDTO);
	// public User findEmail(User user);
	SignUpResponse userRegistration(@Valid SignUpRequest dto);
	//public boolean sendEmail(String subject, String message, String to);
	User findUserByEmail(String email);
	
	

}
