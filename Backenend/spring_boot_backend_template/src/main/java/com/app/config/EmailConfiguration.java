package com.app.config;

import java.util.Properties;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.entities.User;

@Configuration
public class EmailConfiguration {

	@Value("${spring.mail.host}")
	private String mailHost;
	
	@Value("${spring.mail.port}")
	private String mailPort;
	
	@Value("${spring.mail.username}")
	private String userName;
	
	@Value("${spring.mail.password}")
	private String userPassword;

	@Bean
	public JavaMailSender getMailSender()
	{
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		mailSender.setHost(mailHost);
		mailSender.setPort(Integer.parseInt(mailPort));
		mailSender.setUsername(userName);
		mailSender.setPassword(userPassword);
		

Properties props=mailSender.getJavaMailProperties();
		/**
		 * props.put("mail.smtp.starttls", "true"): This sets the "mail.smtp.starttls" property to "true,"
		 * enabling the use of Transport Layer Security (TLS)
		 * for secure communication with the SMTP server
		 * */
		props.put("mail.smtp.starttls.enable", "true");
		

		return mailSender;
	}
}
//
//public ApiResponse forgotPassword(String email) {
//	String otp=optUtils.generateOtp();
//	User user=UserDao.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
//	if(user!=null)
//	{
//		try {
//			emailUtil.sendOtpEmail(email,otp);
//			user.setOtp(otp);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException("Unable to send otp,please try again");
//		}
//	}
//	return new ApiResponse("email sent successfully,check you email");
//}
//
///**
// * Otp will be verified and store in the DB
// * */
//@Override
//public OtpAndEmailResponse verifyOtp(UserAndOtp userRequest) {
//	String email=userRequest.getEmail();
//	UserEntity user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
//	String otp=user.getOtp();
//	if(user!=null)
//	{
//		if(userRequest.getOtp().equals(otp))
//		{
//			return new OtpAndEmailResponse(email,otp);
//		}
//	}
//	return new OtpAndEmailResponse("invalid email","or otp");
//}
//
///**
// * We will fetch the OTP from the DB and the url and
// * then the password will be updated
// * */
//@Override
//public ApiResponse resetPassword(String email, String otp, ResetPassword password) {
//	User user=UserDao.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
//	String dbSavedOtp=user.getOtp();
//	if(otp.equals(dbSavedOtp))
//	{
//		user.setPassword(encoder.encode(password.getPassword()));
//		return new ApiResponse("password updated successfully");
//	}
//	return new ApiResponse("password updation failed");
//}
//


