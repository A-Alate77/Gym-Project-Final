package com.cdac.dtos;

import java.time.LocalDate;

import com.cdac.model.GymMember;

public class GymMemberDTO {
	private int member_id;
	private String gender;
	private String trainer;
	private String paymentStatus;
	private LocalDate joinDate;
	
	private double height;
	private String user_email;
	
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	//private Date joinDate;
	private double weight;
	private int plan_id;
	private int user_id;
	public GymMemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GymMemberDTO(int member_id, String gender, double height, double weight, int plan_id, int user_id) {
		super();
		this.member_id = member_id;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.plan_id = plan_id;
		this.user_id = user_id;
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
	}

	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
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
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public static GymMember updateEntity(GymMember gymMember) {
		
		return gymMember;
	}
	
	public static GymMember toEntity(GymMemberDTO dto) {
		GymMember gymMember = new GymMember();
		
		gymMember.setPaymentStatus(dto.getPaymentStatus());
		gymMember.setTrainer(dto.getTrainer());
		gymMember.setMember_id(dto.getMember_id());
		gymMember.setGender(dto.getGender());
		gymMember.setHeight(dto.getHeight());
		// gymMember.setJoinDate(dto.getJoinDate());
		gymMember.setWeight(dto.getWeight());
		gymMember.setEmail(dto.getUser_email());
		
		

		
		return gymMember;
	}
	
	public static GymMemberDTO fromEntity(GymMember gm) {
		GymMemberDTO dto = new GymMemberDTO();
		dto.setMember_id(gm.getMember_id());
		dto.setGender(gm.getGender());
		dto.setHeight(gm.getHeight());
		dto.setPaymentStatus(gm.getPaymentStatus());
		
		//dto.setJoinDate(gm.getJoinDate());
		dto.setWeight(gm.getWeight());
		dto.setUser_id(gm.getUser().getUser_id());
		dto.setPlan_id(gm.getPlan().getPlan_id());
		
		return dto;
	}
	@Override
	public String toString() {
		return "GymMemberDTO [member_id=" + member_id + ", gender=" + gender + ", paymentStatus=" + paymentStatus
				+ ", joinDate=" + joinDate + ", height=" + height + ", user_email=" + user_email + ", weight=" + weight
				+ ", plan_id=" + plan_id + ", user_id=" + user_id + "]";
	}
	
}
