package com.cdac.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="membershipplan")
public class MembershipPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int plan_id;
	private String plan_name;
	private int duration;
	private int amount;
	
	public MembershipPlan() {
		super();
	}

	public MembershipPlan( String plan_name, int duration, int amount) {
		super();
		
		this.plan_name = plan_name;
		this.duration = duration;
		this.amount = amount;
	}

	public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "MembershipPlan [plan_id=" + plan_id + ", plan_name=" + plan_name + ", duration=" + duration
				+ ", amount=" + amount + "]";
	}
	
	
	
	

}
