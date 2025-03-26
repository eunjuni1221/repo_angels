package com.angels.module.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class UserDto {
	
	private String urSeq;
	private Integer urUse;
	private Integer urDelNy;
	private String urName;
	private Integer urGender;
	private Integer telecom;
	private String phoneNumber;
	private LocalDate urBirth;
	private int age;
	private String urID;
	private String urPassword;
	private LocalDateTime urRegTime;
	private LocalDateTime urModTime;

	
	public String getUrSeq() {
		return urSeq;
	}
	public void setUrSeq(String urSeq) {
		this.urSeq = urSeq;
	}

	public Integer getUrUse() {
		return urUse;
	}
	public void setUrUse(Integer urUse) {
		this.urUse = urUse;
	}
	public Integer getUrDelNy() {
		return urDelNy;
	}
	public void setUrDelNy(Integer urDelNy) {
		this.urDelNy = urDelNy;
	}
	public String getUrName() {
		return urName;
	}
	public void setUrName(String urName) {
		this.urName = urName;
	}
	public Integer getUrGender() {
		return urGender;
	}
	public void setUrGender(Integer urGender) {
		this.urGender = urGender;
	}
	public Integer getTelecom() {
		return telecom;
	}
	public void setTelecom(Integer telecom) {
		this.telecom = telecom;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public LocalDate getUrBirth() {
		return urBirth;
	}
	public void setUrBirth(LocalDate urBirth) {
		this.urBirth = urBirth;
		this.age = calculateAge(urBirth);
	}
	private int calculateAge(LocalDate birthday) {
		if (birthday == null) {
			return 0;
		}
		return Period.between(birthday, LocalDate.now()).getYears();
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public LocalDateTime getUrRegTime() {
		return urRegTime;
	}
	public void setUrRegTime(LocalDateTime urRegTime) {
		this.urRegTime = urRegTime;
	}
	public LocalDateTime getUrModTime() {
		return urModTime;
	}
	public void setUrModTime(LocalDateTime urModTime) {
		this.urModTime = urModTime;
	}
	public String getUrID() {
		return urID;
	}
	public void setUrID(String urID) {
		this.urID = urID;
	}
	public String getUrPassword() {
		return urPassword;
	}
	public void setUrPassword(String urPassword) {
		this.urPassword = urPassword;
	}
	
	

}
