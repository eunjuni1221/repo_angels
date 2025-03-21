package com.angels.module.team;

import java.time.LocalDateTime;

public class TeamDto {
	
	private String tmSeq;
	private String tmHomeName;
	private String tmName;
	private String tmNameAb;
	private String tmHomeNameKor;
	private String tmNameKor;
	private String tmEstYear;
	private Integer tmLeague;
	private Integer tmDiv;
	private LocalDateTime tmRegTime;
	private LocalDateTime tmModTime;
	
	private String cdName;
	private String cdSeq;
	
	
	public String getTmSeq() {
		return tmSeq;
	}
	public void setTmSeq(String tmSeq) {
		this.tmSeq = tmSeq;
	}
	public String getTmName() {
		return tmName;
	}
	public void setTmName(String tmName) {
		this.tmName = tmName;
	}
	public String getTmNameAb() {
		return tmNameAb;
	}
	public void setTmNameAb(String tmNameAb) {
		this.tmNameAb = tmNameAb;
	}
	public String getTmNameKor() {
		return tmNameKor;
	}
	public void setTmNameKor(String tmNameKor) {
		this.tmNameKor = tmNameKor;
	}
	public String getTmEstYear() {
		return tmEstYear;
	}
	public void setTmEstYear(String tmEstYear) {
		this.tmEstYear = tmEstYear;
	}
	public Integer getTmLeague() {
		return tmLeague;
	}
	public void setTmLeague(Integer tmLeague) {
		this.tmLeague = tmLeague;
	}
	public Integer getTmDiv() {
		return tmDiv;
	}
	public void setTmDiv(Integer tmDiv) {
		this.tmDiv = tmDiv;
	}
	public LocalDateTime getTmRegTime() {
		return tmRegTime;
	}
	public void setTmRegTime(LocalDateTime tmRegTime) {
		this.tmRegTime = tmRegTime;
	}
	public LocalDateTime getTmModTime() {
		return tmModTime;
	}
	public void setTmModTime(LocalDateTime tmModTime) {
		this.tmModTime = tmModTime;
	}
	public String getTmHomeName() {
		return tmHomeName;
	}
	public void setTmHomeName(String tmHomeName) {
		this.tmHomeName = tmHomeName;
	}
	public String getTmHomeNameKor() {
		return tmHomeNameKor;
	}
	public void setTmHomeNameKor(String tmHomeNameKor) {
		this.tmHomeNameKor = tmHomeNameKor;
	}
	public String getCdName() {
		return cdName;
	}
	public void setCdName(String cdName) {
		this.cdName = cdName;
	}
	public String getCdSeq() {
		return cdSeq;
	}
	public void setCdSeq(String cdSeq) {
		this.cdSeq = cdSeq;
	}
	
	
	
	
}

