package com.cdac.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dtos.GymMemberDTO;
import com.cdac.model.GymMember;
import com.cdac.model.MembershipPlan;
import com.cdac.model.User;
import com.cdac.service.GymMemberServices;
import com.cdac.service.MembershipPlanService;
import com.cdac.service.UserService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GymMemberDtoController {
	@Autowired
	private GymMemberServices gymMemberService;
	
	@Autowired
	private MembershipPlanService planService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/gymmemberdtos")
	public ResponseEntity<?> saveDetails(GymMemberDTO dto){
		// data input as form-data, so no @RequestBody
	
					 
		//System.out.println("inside DTO");
		System.out.println(dto.toString());
		User user = userService.findById(dto.getUser_id());
		//System.out.println("user: " + user.toString());

		MembershipPlan plan = planService.findById(dto.getPlan_id());
		//System.out.println("plan: " + plan.toString());

		GymMember gymMember = GymMemberDTO.toEntity(dto);
		
		
		//System.out.println("gymmember :" + gymMember.toString());
		gymMember.setUser(user);
		gymMember.setPlan(plan);
		//user.setGymMember(gymMember);
		//userService.save(user);
		//gymMember.setJoinDate(new Date());
		// gymMember = gymMemberservice.save(gymMember);
		GymMember newGymMember = gymMemberService.save(gymMember);

		GymMemberDTO newDto = GymMemberDTO.fromEntity(newGymMember);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
		result.put("data", newDto);
		return ResponseEntity.ok(result);
		}
	

}
