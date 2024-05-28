package com.cdac.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.model.MembershipPlan;
import com.cdac.repository.MembershipPlanRepository;

@Transactional
@Service
public class MembershipPlanService {

	@Autowired 
	private MembershipPlanRepository planRepository;
	
	public MembershipPlan findById(int planId) {
		Optional<MembershipPlan> b = planRepository.findById(planId);
		return b.orElse(null);
	}
	
	public List<MembershipPlan> findAll() {
		return planRepository.findAll();
	}
	
	public MembershipPlan save(MembershipPlan plan) {
		return planRepository.save(plan);
	}
	
	public MembershipPlan update(MembershipPlan plan) {
		return planRepository.save(plan);
	}

	public void deleteById(int planId) {
		planRepository.deleteById(planId);

	}
}
