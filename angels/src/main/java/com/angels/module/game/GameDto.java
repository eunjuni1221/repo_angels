package com.angels.module.game;

import java.util.List;

public class GameDto {
	
	// game 테이블
	private String gmSeq;  
	private String gmGuid; 
	private String gmSeason; 
	private String gmDate; 
	private String gmOfDate; 
	private String gmState; 
	private String gmDeState; 
	private String gmStatusCode; 
	private String gmDayNight; 
	private Integer gmScInnings; 
	private Integer gmIsTie; 
	private String gmSeriesDesc; 
	private String gmDoubleheader; 
	private String gmType; 
	private String gmRegTime; 
	private String gmModTime; 
	
	private String venue_stSeq; 
	private String homeTeam_tmSeq; 
	private String awayTeam_tmSeq;
	
	// game 테이블 LEFT JOIN
	private String stName;
	private String stCity;
	private String tmName;
	private String tmClubName;
	private String tmAbbreviation;
	private String tmLogoUrl;
	
	private String home_tmName;
	private String home_tmClubName;
	private String home_tmAbbreviation;
	private String home_tmLogoUrl;
	private String away_tmName;
	private String away_tmClubName;
	private String away_tmAbbreviation;
	private String away_tmLogoUrl;
	
	// gamelinescore 테이블
	private String glSeq;
	private Integer glHomeRuns;
	private Integer glHomeHits;
	private Integer glHomeErrors;
	private Integer glHomeLob;
	private Integer glHomeWin;
	private Integer glAwayRuns;
	private Integer glAwayHits;
	private Integer glAwayErrors;
	private Integer glAwayLob;
	private Integer glAwayWin;
	private String glRegTime;
	private String glModTime;
	private String game_gmSeq;
	
	//gameInning
	private String giSeq;
	private Integer giInning;
	private Integer giHomeScore;
	private Integer giAwayScore;
	private String giRegTime;
	private String giModTime;
	
	private List<String> awayInningScores;
	private List<String> homeInningScores;
	
	public String getGmSeq() {
		return gmSeq;
	}
	public void setGmSeq(String gmSeq) {
		this.gmSeq = gmSeq;
	}
	public String getGmGuid() {
		return gmGuid;
	}
	public void setGmGuid(String gmGuid) {
		this.gmGuid = gmGuid;
	}
	public String getGmSeason() {
		return gmSeason;
	}
	public void setGmSeason(String gmSeason) {
		this.gmSeason = gmSeason;
	}
	public String getGmDate() {
		return gmDate;
	}
	public void setGmDate(String gmDate) {
		this.gmDate = gmDate;
	}
	public String getGmOfDate() {
		return gmOfDate;
	}
	public void setGmOfDate(String gmOfDate) {
		this.gmOfDate = gmOfDate;
	}
	public String getGmState() {
		return gmState;
	}
	public void setGmState(String gmState) {
		this.gmState = gmState;
	}
	public String getGmDeState() {
		return gmDeState;
	}
	public void setGmDeState(String gmDeState) {
		this.gmDeState = gmDeState;
	}
	public String getGmStatusCode() {
		return gmStatusCode;
	}
	public void setGmStatusCode(String gmStatusCode) {
		this.gmStatusCode = gmStatusCode;
	}
	public String getGmDayNight() {
		return gmDayNight;
	}
	public void setGmDayNight(String gmDayNight) {
		this.gmDayNight = gmDayNight;
	}
	public Integer getGmScInnings() {
		return gmScInnings;
	}
	public void setGmScInnings(Integer gmScInnings) {
		this.gmScInnings = gmScInnings;
	}
	public Integer getGmIsTie() {
		return gmIsTie;
	}
	public void setGmIsTie(Integer gmIsTie) {
		this.gmIsTie = gmIsTie;
	}
	public String getGmSeriesDesc() {
		return gmSeriesDesc;
	}
	public void setGmSeriesDesc(String gmSeriesDesc) {
		this.gmSeriesDesc = gmSeriesDesc;
	}
	public String getGmDoubleheader() {
		return gmDoubleheader;
	}
	public void setGmDoubleheader(String gmDoubleheader) {
		this.gmDoubleheader = gmDoubleheader;
	}
	public String getGmType() {
		return gmType;
	}
	public void setGmType(String gmType) {
		this.gmType = gmType;
	}
	public String getGmRegTime() {
		return gmRegTime;
	}
	public void setGmRegTime(String gmRegTime) {
		this.gmRegTime = gmRegTime;
	}
	public String getGmModTime() {
		return gmModTime;
	}
	public void setGmModTime(String gmModTime) {
		this.gmModTime = gmModTime;
	}
	public String getVenue_stSeq() {
		return venue_stSeq;
	}
	public void setVenue_stSeq(String venue_stSeq) {
		this.venue_stSeq = venue_stSeq;
	}
	public String getHomeTeam_tmSeq() {
		return homeTeam_tmSeq;
	}
	public void setHomeTeam_tmSeq(String homeTeam_tmSeq) {
		this.homeTeam_tmSeq = homeTeam_tmSeq;
	}
	public String getAwayTeam_tmSeq() {
		return awayTeam_tmSeq;
	}
	public void setAwayTeam_tmSeq(String awayTeam_tmSeq) {
		this.awayTeam_tmSeq = awayTeam_tmSeq;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public String getStCity() {
		return stCity;
	}
	public void setStCity(String stCity) {
		this.stCity = stCity;
	}
	public String getTmName() {
		return tmName;
	}
	public void setTmName(String tmName) {
		this.tmName = tmName;
	}
	public String getTmClubName() {
		return tmClubName;
	}
	public void setTmClubName(String tmClubName) {
		this.tmClubName = tmClubName;
	}
	public String getTmAbbreviation() {
		return tmAbbreviation;
	}
	public void setTmAbbreviation(String tmAbbreviation) {
		this.tmAbbreviation = tmAbbreviation;
	}
	public String getTmLogoUrl() {
		return tmLogoUrl;
	}
	public void setTmLogoUrl(String tmLogoUrl) {
		this.tmLogoUrl = tmLogoUrl;
	}
	public String getHome_tmName() {
		return home_tmName;
	}
	public void setHome_tmName(String home_tmName) {
		this.home_tmName = home_tmName;
	}
	public String getHome_tmClubName() {
		return home_tmClubName;
	}
	public void setHome_tmClubName(String home_tmClubName) {
		this.home_tmClubName = home_tmClubName;
	}
	public String getHome_tmAbbreviation() {
		return home_tmAbbreviation;
	}
	public void setHome_tmAbbreviation(String home_tmAbbreviation) {
		this.home_tmAbbreviation = home_tmAbbreviation;
	}
	public String getHome_tmLogoUrl() {
		return home_tmLogoUrl;
	}
	public void setHome_tmLogoUrl(String home_tmLogoUrl) {
		this.home_tmLogoUrl = home_tmLogoUrl;
	}
	public String getAway_tmName() {
		return away_tmName;
	}
	public void setAway_tmName(String away_tmName) {
		this.away_tmName = away_tmName;
	}
	public String getAway_tmClubName() {
		return away_tmClubName;
	}
	public void setAway_tmClubName(String away_tmClubName) {
		this.away_tmClubName = away_tmClubName;
	}
	public String getAway_tmAbbreviation() {
		return away_tmAbbreviation;
	}
	public void setAway_tmAbbreviation(String away_tmAbbreviation) {
		this.away_tmAbbreviation = away_tmAbbreviation;
	}
	public String getAway_tmLogoUrl() {
		return away_tmLogoUrl;
	}
	public void setAway_tmLogoUrl(String away_tmLogoUrl) {
		this.away_tmLogoUrl = away_tmLogoUrl;
	}
	public String getGlSeq() {
		return glSeq;
	}
	public void setGlSeq(String glSeq) {
		this.glSeq = glSeq;
	}
	public Integer getGlHomeRuns() {
		return glHomeRuns;
	}
	public void setGlHomeRuns(Integer glHomeRuns) {
		this.glHomeRuns = glHomeRuns;
	}
	public Integer getGlHomeHits() {
		return glHomeHits;
	}
	public void setGlHomeHits(Integer glHomeHits) {
		this.glHomeHits = glHomeHits;
	}
	public Integer getGlHomeErrors() {
		return glHomeErrors;
	}
	public void setGlHomeErrors(Integer glHomeErrors) {
		this.glHomeErrors = glHomeErrors;
	}
	public Integer getGlHomeLob() {
		return glHomeLob;
	}
	public void setGlHomeLob(Integer glHomeLob) {
		this.glHomeLob = glHomeLob;
	}
	public Integer getGlHomeWin() {
		return glHomeWin;
	}
	public void setGlHomeWin(Integer glHomeWin) {
		this.glHomeWin = glHomeWin;
	}
	public Integer getGlAwayRuns() {
		return glAwayRuns;
	}
	public void setGlAwayRuns(Integer glAwayRuns) {
		this.glAwayRuns = glAwayRuns;
	}
	public Integer getGlAwayHits() {
		return glAwayHits;
	}
	public void setGlAwayHits(Integer glAwayHits) {
		this.glAwayHits = glAwayHits;
	}
	public Integer getGlAwayLob() {
		return glAwayLob;
	}
	public void setGlAwayLob(Integer glAwayLob) {
		this.glAwayLob = glAwayLob;
	}
	public Integer getGlAwayWin() {
		return glAwayWin;
	}
	public void setGlAwayWin(Integer glAwayWin) {
		this.glAwayWin = glAwayWin;
	}
	public String getGlRegTime() {
		return glRegTime;
	}
	public void setGlRegTime(String glRegTime) {
		this.glRegTime = glRegTime;
	}
	public String getGlModTime() {
		return glModTime;
	}
	public void setGlModTime(String glModTime) {
		this.glModTime = glModTime;
	}
	public String getGame_gmSeq() {
		return game_gmSeq;
	}
	public void setGame_gmSeq(String game_gmSeq) {
		this.game_gmSeq = game_gmSeq;
	}
	public String getGiSeq() {
		return giSeq;
	}
	public void setGiSeq(String giSeq) {
		this.giSeq = giSeq;
	}
	public Integer getGiInning() {
		return giInning;
	}
	public void setGiInning(Integer giInning) {
		this.giInning = giInning;
	}
	public Integer getGiHomeScore() {
		return giHomeScore;
	}
	public void setGiHomeScore(Integer giHomeScore) {
		this.giHomeScore = giHomeScore;
	}
	public Integer getGiAwayScore() {
		return giAwayScore;
	}
	public void setGiAwayScore(Integer giAwayScore) {
		this.giAwayScore = giAwayScore;
	}
	public String getGiRegTime() {
		return giRegTime;
	}
	public void setGiRegTime(String giRegTime) {
		this.giRegTime = giRegTime;
	}
	public String getGiModTime() {
		return giModTime;
	}
	public void setGiModTime(String giModTime) {
		this.giModTime = giModTime;
	}
	public Integer getGlAwayErrors() {
		return glAwayErrors;
	}
	public void setGlAwayErrors(Integer glAwayErrors) {
		this.glAwayErrors = glAwayErrors;
	}
	public List<String> getAwayInningScores() {
		return awayInningScores;
	}
	public void setAwayInningScores(List<String> awayInningScores) {
		this.awayInningScores = awayInningScores;
	}
	public List<String> getHomeInningScores() {
		return homeInningScores;
	}
	public void setHomeInningScores(List<String> homeInningScores) {
		this.homeInningScores = homeInningScores;
	}
	
	
	
}
