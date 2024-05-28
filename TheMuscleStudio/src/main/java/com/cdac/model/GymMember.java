package com.cdac.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="gymMember")
public class GymMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int member_id;
	@Column(name="user_email")
	private String email;
	
	@NotEmpty
	private String gender;
	private String trainer;
	private String dietPlan;
	
	
	private String paymentStatus;
	private LocalDate joinDate;
	private LocalDate expiryDate;
	
	private double height;
	
	private double weight;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="plan_id")
	private MembershipPlan plan;

	public GymMember() {
		super();
	}

	public GymMember( String gender, double height, double weight, User user, MembershipPlan plan) {
		super();
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.user = user;
		this.plan = plan;
	}

	

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		System.out.println(expiryDate);
		this.expiryDate = expiryDate;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
		  LocalDate expiryDate = joinDate.plusMonths(2);
	      System.out.println("The LocalDate after adding 2 months is: " + expiryDate);
	   //System.out.println(plan.getDuration());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MembershipPlan getPlan() {
		return plan;
	}

	public void setPlan(MembershipPlan plan) {
		this.plan = plan;
	}

	public String getDietPlan() {
		return dietPlan;
	}

	public void setDietPlan(String dietPlan) {
		this.dietPlan = dietPlan;
	}

	

	@Override
	public String toString() {
		return "GymMember [member_id=" + member_id + ", email=" + email + ", gender=" + gender + ", trainer=" + trainer
				+ ", paymentStatus=" + paymentStatus + ", joinDate=" + joinDate + ", height=" + height + ", weight="
				+ weight + ", user=" + user + ", plan=" + plan + "]";
	}
	
	
}
