package com.angels.module.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import com.angels.module.Base.BaseDto;

public class UserDto extends BaseDto{
	
	private String urSeq;
	private Integer urUse;
	private Integer urDelNy;
	private String urName;
	private String urNickname;
	private Integer urGender;
	private Integer telecom;
	private String phoneNumber;
	private LocalDate urBirth;
	private int age;
	private String urID;
	private String emailID;
	private String email;
	private String urPassword;
	private String urNewPassword;
	private LocalDateTime urRegTime;
	private LocalDateTime urModTime;
	private String urPostNumber;
	private String urAddress;
	private String urDetailAddress;
	private String urLatitude;
	private String urLongitude;
	
	
	
	
	public String getUrPostNumber() {
		return urPostNumber;
	}
	public void setUrPostNumber(String urPostNumber) {
		this.urPostNumber = urPostNumber;
	}
	public String getUrAddress() {
		return urAddress;
	}
	public void setUrAddress(String urAddress) {
		this.urAddress = urAddress;
	}
	public String getUrDetailAddress() {
		return urDetailAddress;
	}
	public void setUrDetailAddress(String urDetailAddress) {
		this.urDetailAddress = urDetailAddress;
	}
	public String getUrLatitude() {
		return urLatitude;
	}
	public void setUrLatitude(String urLatitude) {
		this.urLatitude = urLatitude;
	}
	public String getUrLongitude() {
		return urLongitude;
	}
	public void setUrLongitude(String urLongitude) {
		this.urLongitude = urLongitude;
	}
	public String getUrNewPassword() {
		return urNewPassword;
	}
	public void setUrNewPassword(String urNewPassword) {
		this.urNewPassword = urNewPassword;
	}
	public String getUrNickname() {
		return urNickname;
	}
	public void setUrNickname(String urNickname) {
		this.urNickname = urNickname;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
