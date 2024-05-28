package com.cdac.controller;




import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cdac.model.User;
import com.cdac.service.EmailService;
import com.cdac.service.Response;
import com.cdac.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/authenticateUser")
	public ResponseEntity<?> authenticateUser(User user) {
		
		User authuser = userService.findByEmail(user.getEmail());
		
		Base64.Decoder decoder = Base64.getDecoder();
System.out.println(authuser.getPassword());
		byte[] decodedByteArray = decoder.decode(authuser.getPassword());
		System.out.println(new String(decodedByteArray));
		if(authuser!=null ){
			if((new String(decodedByteArray)).equals(user.getPassword()) ){
				
				return Response.success(authuser);
			}
		}
		return Response.error(authuser);
	}
	@PostMapping("/forgotpassword")
	public ResponseEntity<?> forgotpassword(User user) {
		
		User authuser = userService.findByEmail(user.getEmail());
		if(authuser!=null ){
			System.out.println(user.getOtp());
			authuser.setOtp(user.getOtp());
			userService.save(authuser);
			
			emailService.sendSimpleEmail(user.getEmail(),
					"OTP : "+user.getOtp(),
					"Don Not Share Your OTP");
			
				return Response.success(authuser);
			
		}
		return Response.error(authuser);
	}
	
	
}
