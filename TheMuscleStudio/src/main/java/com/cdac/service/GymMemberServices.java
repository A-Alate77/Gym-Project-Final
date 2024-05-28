package com.cdac.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.model.GymMember;
import com.cdac.repository.GymMemberRepository;

@Transactional
@Service

public class GymMemberServices {

	@Autowired 
	private GymMemberRepository gymMemberRepo;
  
	public GymMember findById(int memberId) {
		Optional<GymMember> b = gymMemberRepo.findById(memberId);
		return b.orElse(null);
	}
  
	public List<GymMember> findAll() {
		return gymMemberRepo.findAll();
	}

	public GymMember save(GymMember gm) {
		return gymMemberRepo.save(gm);
	}
	
	public GymMember update(GymMember gm) {
		return gymMemberRepo.save(gm);
	}

	public void deleteById(int memberId) {
		gymMemberRepo.deleteById(memberId);

	}
	public void delete (GymMember user) {
		gymMemberRepo.delete(user);
	}

	public GymMember findByEmail(String email) {
		GymMember member = gymMemberRepo.findByEmail(email);
		return member;
		
	}

}
