package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.entities.ForgotPasswordRequest;
import com.app.entities.User;
import com.app.service.OtpService;
import com.app.service.UserService;
@RestController
@RequestMapping("/api")

public class ForgotPasswordController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private OtpService otpService;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        String email = forgotPasswordRequest.getEmail();
        User user = userService.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Generate and send OTP
        String otp = generateOTP();
        otpService.sendOTPEmail(email, otp); // Assuming you have sendOTPEmail method in OtpService

        // Save OTP for verification
        otpService.saveOTP(email, otp);

        return ResponseEntity.ok("OTP sent to your email");
    }

    // Method to generate OTP (you can use any logic to generate OTP)
    private String generateOTP() {
        int otpLength = 6; // You can customize the length of the OTP as needed
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < otpLength; i++) {
            int digit = (int) (Math.random() * 10); // Generate a random digit (0 to 9)
            otp.append(digit);
        }

        return otp.toString();
    }
}