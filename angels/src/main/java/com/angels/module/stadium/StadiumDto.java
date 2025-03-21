package com.angels.module.stadium;

import java.time.LocalDateTime;

public class StadiumDto {
	
	private String stSeq;
	private String stName;
	private String stNameKor;
	private String stAdress;
	private LocalDateTime stRegTime;
	private LocalDateTime stModTime;

	private String team_tmSeq;
	private String tmNameAb;
    private String tmSeq;

	public String getStSeq() {
		return stSeq;
	}

	public void setStSeq(String stSeq) {
		this.stSeq = stSeq;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public String getStNameKor() {
		return stNameKor;
	}

	public void setStNameKor(String stNameKor) {
		this.stNameKor = stNameKor;
	}

	public String getStAdress() {
		return stAdress;
	}

	public void setStAdress(String stAdress) {
		this.stAdress = stAdress;
	}

	public String getTeam_tmSeq() {
		return team_tmSeq;
	}

	public void setTeam_tmSeq(String team_tmSeq) {
		this.team_tmSeq = team_tmSeq;
	}

	public LocalDateTime getStRegTime() {
		return stRegTime;
	}

	public void setStRegTime(LocalDateTime stRegTime) {
		this.stRegTime = stRegTime;
	}

	public LocalDateTime getStModTime() {
		return stModTime;
	}

	public void setStModTime(LocalDateTime stModTime) {
		this.stModTime = stModTime;
	}

	public String getTmNameAb() {
		return tmNameAb;
	}

	public void setTmNameAb(String tmNameAb) {
		this.tmNameAb = tmNameAb;
	}

	public String getTmSeq() {
		return tmSeq;
	}

	public void setTmSeq(String tmSeq) {
		this.tmSeq = tmSeq;
	}
	
	
	
	
}
