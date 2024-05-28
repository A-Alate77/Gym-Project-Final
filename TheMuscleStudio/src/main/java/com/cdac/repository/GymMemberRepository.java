package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.model.GymMember;

@Repository
public interface GymMemberRepository extends JpaRepository<GymMember, Integer>{
	GymMember findByEmail(String email);
}
