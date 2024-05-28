package com.cdac.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.model.User;
import com.cdac.repository.UserRepository;

@Transactional
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User save(User user){
		
		Base64.Encoder encoder = Base64.getEncoder();
		String normalString = user.getPassword();
		
		String encodedString = encoder.encodeToString(
		        normalString.getBytes(StandardCharsets.UTF_8) );
		user.setPassword(encodedString);
		return userRepository.save(user);
	}
	
	public User findById (int userId) {
		Optional<User> user = userRepository.findById(userId);
		return user.orElse(null);
	}
	
	public void delete (User user) {
		userRepository.delete(user);
	}

	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	public List<User> findByRole(String role) {
		
		return userRepository.findByRole(role);
	}
	
}
