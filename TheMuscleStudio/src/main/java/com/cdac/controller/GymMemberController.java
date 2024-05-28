package com.cdac.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.model.GymMember;
import com.cdac.model.MembershipPlan;
import com.cdac.service.EmailService;
import com.cdac.service.GymMemberServices;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GymMemberController {
	
	@Autowired
	private GymMemberServices gymMemberService;
	
	@Autowired
	private EmailService emailService;
	
	
	
	
	   //get Gym Member  by Id
		@GetMapping("/gymmembers/{memberId}")
		public ResponseEntity<?>findById(@PathVariable("memberId") int id) {
			Map<String,Object>map= new HashMap<>();
			GymMember gymmember = gymMemberService.findById(id);
			 map.put("status", "success");
			 map.put("data", gymmember);
			return ResponseEntity.ok(map);
		}
		
		//get Gym Member  by email
				@GetMapping("/gymmember/{email}")
				public ResponseEntity<?>findByEmail(@PathVariable("email") String email) {
					Map<String,Object>map= new HashMap<>();
					GymMember gymmember = gymMemberService.findByEmail(email);
					 map.put("status", "success");
					 map.put("data", gymmember);
					return ResponseEntity.ok(map);
				}
	
		
		//get all GymMembers
		@GetMapping("/gymmembers")
		public ResponseEntity<List<GymMember>> findAll() {
			List<GymMember> list = gymMemberService.findAll();
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		
	
//	  
		
	 	//update GymMembers
		@PutMapping("/gymmembers/update/{email}")
		public ResponseEntity<GymMember> update(@PathVariable("email") String email,@RequestBody GymMember memberDetails){
			GymMember gymmember = gymMemberService.findByEmail(email);
			System.out.println(memberDetails.toString());
			System.out.println(memberDetails.getWeight());
			//gymmember.setWeight(memberDetails.getWeight());
			gymmember.setPaymentStatus(memberDetails.getPaymentStatus());
			gymmember.setTrainer(memberDetails.getTrainer());
			gymmember.setJoinDate(memberDetails.getJoinDate());
			//===============================
//			MembershipPlan plan = planService.findById(gymmember.getPlan());
			MembershipPlan plan = gymmember.getPlan();
			//
			System.out.println(plan.getDuration());
			LocalDate date =memberDetails.getJoinDate();
			 emailService.sendSimpleEmail(gymmember.getEmail(),
						"Your The Muscle Studio Membership Approved",
						"Please login to see details of plan and trainer assigned to you");
		        
		        
			gymmember.setExpiryDate(date.plusMonths(plan.getDuration()));
			
			System.out.println(plan.toString());
			//==============================
		   GymMember newUser = gymMemberService.update(gymmember);
		   return ResponseEntity.ok(newUser);
	   }
		
		//delete gymmember
		@DeleteMapping("/gymmembers/delete/{memberId}")
		public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable("memberId") int memberId){
			GymMember user = gymMemberService.findById(memberId);
					
			gymMemberService.delete(user);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
}
