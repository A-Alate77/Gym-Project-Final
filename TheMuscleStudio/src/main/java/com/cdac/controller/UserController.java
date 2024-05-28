package com.cdac.controller;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.model.User;
import com.cdac.service.EmailService;
import com.cdac.service.Response;
import com.cdac.service.UserService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;

	
	// get all users
		@GetMapping("/user/list/{role}")
		public ResponseEntity<List<User>> getAllUser(@PathVariable String role){
			
			 List<User> list = userService.findByRole(role);
			 return new ResponseEntity<>(list,HttpStatus.OK);
		}
	
	// add user
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User user2 = userService.findByEmail(user.getEmail());
		if(user2!=null) {
			System.out.println(user2.toString());
			return null;
		}else {
			User newUser = userService.save(user);
			emailService.sendSimpleEmail(user.getEmail(),
					"Welcome to The GYM Freak Studio",
					"Registered Successfully. Purchase our membership plan to get all services");
			return ResponseEntity.ok(newUser);
		}
	}
	
	// get user by id
	@GetMapping("/user/{user_id}")
	public ResponseEntity<User> getUserById (@PathVariable("user_id") int userId) {
		User user = userService.findById(userId);
				
		return ResponseEntity.ok(user);
	}
	
	// update user
	@PutMapping("/user/{user_id}")
	public ResponseEntity<User> updateUser (@PathVariable("user_id") int userId,@RequestBody User userDetails) { 
		User user = userService.findById(userId);
				
		user.setFirst_name(userDetails.getFirst_name());
		user.setLast_name(userDetails.getLast_name());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
		user.setAddress(userDetails.getAddress());
		user.setContact(userDetails.getContact());
		
		User updateUser = userService.save(user);
		return ResponseEntity.ok(updateUser);
	}
	
	// update user
		@PutMapping("/updatepassword")
		public ResponseEntity<?> updatePassword ( User user) { 
			
			User authuser = userService.findByEmail(user.getEmail());
			
			Base64.Decoder decoder = Base64.getDecoder();
			System.out.println(authuser.getPassword());
			byte[] decodedByteArray = decoder.decode(authuser.getPassword());
			byte[] decodedByteArray2 = decoder.decode(decodedByteArray);
			System.out.println(new String(decodedByteArray2));
			String databasePassword= new String (decodedByteArray2);
			System.out.println(databasePassword);
			
			if(authuser!=null ){
				
				if(databasePassword.equals(user.getPassword()) ){
					System.out.println("old password is used");
					return Response.error(authuser);
				}
				
				if(authuser.getOtp()==user.getOtp() ){
					System.out.println("otp matched successfully");
					authuser.setOtp(0);
					authuser.setPassword(user.getPassword());
					userService.save(authuser);
				
				
				emailService.sendSimpleEmail(user.getEmail(),
						"Password Changed",
						"If not done by you please contact The Muscle Studio");
				return Response.success(authuser);
				}	
			}
			return Response.error(authuser);
		}
	
	// delete user
	@DeleteMapping("/user/{user_id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable("user_id") int userId){
		User user = userService.findById(userId);
				
		userService.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
