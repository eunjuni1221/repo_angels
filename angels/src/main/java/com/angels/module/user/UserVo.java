package com.angels.module.user;

import com.angels.module.Base.BaseVo;

public class UserVo extends BaseVo{

	private String shTelecom;
	private String shGender;
	private String shEmail;
	
	private String urSeq;
	private String urID;
	private String urPassword;
	
	
	
	
	public String getShEmail() {
		return shEmail;
	}



	public void setShEmail(String shEmail) {
		this.shEmail = shEmail;
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

	public String getShTelecom() {
		return shTelecom;
	}



	public void setShTelecom(String shTelecom) {
		this.shTelecom = shTelecom;
	}



	public String getShGender() {
		return shGender;
	}



	public void setShGender(String shGender) {
		this.shGender = shGender;
	}



	public String getUrSeq() {
		return urSeq;
	}



	public void setUrSeq(String urSeq) {
		this.urSeq = urSeq;
	}

}
