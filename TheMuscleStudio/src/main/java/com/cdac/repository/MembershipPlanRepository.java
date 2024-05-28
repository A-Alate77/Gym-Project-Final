package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.model.MembershipPlan;


@Repository
public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, Integer>{

}
