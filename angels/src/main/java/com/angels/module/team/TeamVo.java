package com.angels.module.team;

import com.angels.module.Base.BaseVo;

public class TeamVo extends BaseVo{

	private Integer shLeagueOption;
	private Integer shDivOption;
	
	private String tmSeq;
	

	public String getTmSeq() {
		return tmSeq;
	}


	public void setTmSeq(String tmSeq) {
		this.tmSeq = tmSeq;
	}


	public Integer getShLeagueOption() {
		return shLeagueOption;
	}


	public void setShLeagueOption(Integer shLeagueOption) {
		this.shLeagueOption = shLeagueOption;
	}


	public Integer getShDivOption() {
		return shDivOption;
	}


	public void setShDivOption(Integer shDivOption) {
		this.shDivOption = shDivOption;
	}




}
