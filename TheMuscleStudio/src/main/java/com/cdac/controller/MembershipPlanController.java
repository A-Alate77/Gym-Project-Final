package com.cdac.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.model.MembershipPlan;
import com.cdac.service.MembershipPlanService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MembershipPlanController {
	
	@Autowired
	private MembershipPlanService planService;
	
	   //get plans by Id
		@GetMapping("/plans/{planId}")
		public ResponseEntity<?>findById(@PathVariable("planId") int id) {
			Map<String,Object>map= new HashMap<>();
			MembershipPlan plan = planService.findById(id);
			 map.put("status", "success");
			 map.put("data", plan);
			return ResponseEntity.ok(map);
		}
		
		//get all plans
		@GetMapping("/plans")
		public ResponseEntity<List<MembershipPlan>> findAll() {
			List<MembershipPlan> list = planService.findAll();
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		
	    //save plans
		@PostMapping("/plans/save")
		public ResponseEntity<MembershipPlan> save(@RequestBody MembershipPlan plan){
			MembershipPlan newMembershipPlan = planService.save(plan);
		   return ResponseEntity.ok(newMembershipPlan);
	   }
		
	  	//update plans
		@PutMapping("/plans/update/{planId}")
		public ResponseEntity<MembershipPlan> update(@PathVariable("planId") int id,@RequestBody MembershipPlan pl){
		   pl.setPlan_id(id);
		   MembershipPlan newMembershipPlan = planService.update(pl);
		   return ResponseEntity.ok(newMembershipPlan);
	   }
		
		 //delete plan
		  @DeleteMapping("/plans/delete/{planId}")
		public void delete(@PathVariable("planId") int id){
			  planService.deleteById(id);
		  }
}
