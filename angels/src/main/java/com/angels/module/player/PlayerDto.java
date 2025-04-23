package com.angels.module.player;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import com.angels.module.Base.BaseDto;

public class PlayerDto extends BaseDto{

	private String pySeq;
	private String pyGivenName;
	private String pyFamilyName;
	private String pyOriName;
	private String pyFamliyNameKor;
	private String pyGivenNameKor;
	private String pyOriNameKor;
	private String homeTown;
	private String nationality;
	private Integer throwHand;
	private Integer batHand;
	private int jerseyNumber;
	private int heightCm;
	private String heightFt;
	private int weightKg;
	private int weightLb;
	private String finalSchoolHistory;
	private LocalDate debut;
	private String nickName;
	private String Draft;
	private int status;
	private LocalDateTime pyRegTime;
	private LocalDateTime pyModTime;
	private Integer pyUse;
	private Integer pyDelNy;
	private LocalDate birthday;
	private int age;
	private int position;
	
	private Integer team_tmSeq;
	private String tmNameAb;
	private String tmSeq;

	public String getPySeq() {
		return pySeq;
	}

	public void setPySeq(String pySeq) {
		this.pySeq = pySeq;
	}

	public String getPyGivenName() {
		return pyGivenName;
	}

	public void setPyGivenName(String pyGivenName) {
		this.pyGivenName = pyGivenName;
	}

	public String getPyFamilyName() {
		return pyFamilyName;
	}

	public void setPyFamilyName(String pyFamilyName) {
		this.pyFamilyName = pyFamilyName;
	}

	public String getPyOriName() {
		return pyOriName;
	}

	public void setPyOriName(String pyOriName) {
		this.pyOriName = pyOriName;
	}

	public String getPyFamliyNameKor() {
		return pyFamliyNameKor;
	}

	public void setPyFamliyNameKor(String pyFamliyNameKor) {
		this.pyFamliyNameKor = pyFamliyNameKor;
	}

	public String getPyGivenNameKor() {
		return pyGivenNameKor;
	}

	public void setPyGivenNameKor(String pyGivenNameKor) {
		this.pyGivenNameKor = pyGivenNameKor;
	}

	public String getPyOriNameKor() {
		return pyOriNameKor;
	}

	public void setPyOriNameKor(String pyOriNameKor) {
		this.pyOriNameKor = pyOriNameKor;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getThrowHand() {
		return throwHand;
	}

	public void setThrowHand(Integer throwHand) {
		this.throwHand = throwHand;
	}

	public Integer getBatHand() {
		return batHand;
	}

	public void setBatHand(Integer batHand) {
		this.batHand = batHand;
	}

	public int getJerseyNumber() {
		return jerseyNumber;
	}

	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}

	public int getHeightCm() {
		return heightCm;
	}

	public void setHeightCm(int heightCm) {
		this.heightCm = heightCm;
	}

	public String getHeightFt() {
		return heightFt;
	}

	public void setHeightFt(String heightFt) {
		this.heightFt = heightFt;
	}

	public int getWeightKg() {
		return weightKg;
	}

	public void setWeightKg(int weightKg) {
		this.weightKg = weightKg;
	}

	public int getWeightLb() {
		return weightLb;
	}

	public void setWeightLb(int weightLb) {
		this.weightLb = weightLb;
	}

	public String getFinalSchoolHistory() {
		return finalSchoolHistory;
	}

	public void setFinalSchoolHistory(String finalSchoolHistory) {
		this.finalSchoolHistory = finalSchoolHistory;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDraft() {
		return Draft;
	}

	public void setDraft(String draft) {
		Draft = draft;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getPyRegTime() {
		return pyRegTime;
	}

	public void setPyRegTime(LocalDateTime pyRegTime) {
		this.pyRegTime = pyRegTime;
	}

	public LocalDateTime getPyModTime() {
		return pyModTime;
	}

	public void setPyModTime(LocalDateTime pyModTime) {
		this.pyModTime = pyModTime;
	}

	public Integer getPyUse() {
		return pyUse;
	}

	public void setPyUse(Integer pyUse) {
		this.pyUse = pyUse;
	}

	public Integer getPyDelNy() {
		return pyDelNy;
	}

	public void setPyDelNy(Integer pyDelNy) {
		this.pyDelNy = pyDelNy;
	}

	public Integer getTeam_tmSeq() {
		return team_tmSeq;
	}

	public void setTeam_tmSeq(Integer team_tmSeq) {
		this.team_tmSeq = team_tmSeq;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		this.age = calculateAge(birthday);
	}

	public int getAge() {
		return age;
	}

	private int calculateAge(LocalDate birthday) {
		if (birthday == null) {
			return 0;
		}
		return Period.between(birthday, LocalDate.now()).getYears();
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
	
	
}
