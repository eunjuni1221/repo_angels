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
	private String tmCityName;
	
	private String home_tmName;
	private String home_tmClubName;
	private String home_tmAbbreviation;
	private String home_tmLogoUrl;
	private String home_tmCityName;
	private String away_tmName;
	private String away_tmClubName;
	private String away_tmAbbreviation;
	private String away_tmLogoUrl;
	private String away_tmCityName;
	
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
	
	private List<GameDto> inningList;
	
	// gameBoxscore
	private String gbSeq;
	private Integer gbRuns;
	private Integer gbHits;
	private Integer gbErrors;
	private Integer gbLob;
	private Integer gbDp;
	private Integer gbTrip;
	private double gbAvg; 
	private double gbObp;
	private double gbSlg;
	private double gbOps;
	private Integer gbSteal;
	private Integer gbCauSteal;
	private Integer gbSacBun;
	private Integer gbSacFly;
	private Integer gbRbi;
	private String gbRegTime;
	private String gbModTime;
	private String team_tmSeq;
	
	// gameMeta
	private String gtSeq;
	private Integer gtAttendance;
	private String gtWeather;
	private String gtWind;
	private String gtUmpireHome;
	private String gtUmpireFirst;
	private String gtUmpireSecond;
	private String gtUmpireThird;
	private String gtUmpireLeft;
	private String gtUmpireRight;
	private String gtRegTime;
	private String gtModTime;

	//playerBatting
	private String pbSeq;
	private Integer pbIsStarter;
	private Integer pbBattingOrder;
	private Integer pbAb;
	private Integer pbRuns;
	private Integer pbHits;
	private Integer pbRbi;
	private Integer pbBb; 
	private Integer pbSo;
	private Integer pbHomerun;
	private Integer pbDouble;
	private Integer pbTriple;
	private Integer pbSteal;
	private Integer pbCauSteal;
	private Integer pbSacBunt;
	private Integer pbSacFly;
	private double pbAvg;
	private String pbRegTime;
	private String pbModTime;
	private String player_pySeq;
	
	// playerPitching
	private String ppSeq;
	private Integer ppIsStarter;
	private double ppIp;
	private Integer ppHit;
	private Integer ppRun;
	private Integer ppEaruns;
	private Integer ppBb;
	private Integer ppSo; 
	private Integer ppHr;
	private Integer ppPitchCount;
	private Integer ppStrike;
	private double ppEra;
	private String ppRegTime;
	private String ppModtime;
	
	// playerDefense
	private String pdSeq;
	private String pdPosition;
	private Integer pdError;	

	// pitchingResult
	private String prSeq;
	private String prResult;
	private String prRegTime;
	private String prModTime;
	
	// playerSub
	private String psSeq;
	private String psPosition;
	private Integer psInning;
	private Integer psBattingOrder;
	private String psSubType;
	private String psRegTime;
	private String psModTime;
	private String playerOut_pySeq;
	private String playerIn_pySeq;

	
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
	public String getTmCityName() {
		return tmCityName;
	}
	public void setTmCityName(String tmCityName) {
		this.tmCityName = tmCityName;
	}
	public String getHome_tmCityName() {
		return home_tmCityName;
	}
	public void setHome_tmCityName(String home_tmCityName) {
		this.home_tmCityName = home_tmCityName;
	}
	public String getAway_tmCityName() {
		return away_tmCityName;
	}
	public void setAway_tmCityName(String away_tmCityName) {
		this.away_tmCityName = away_tmCityName;
	}
	public List<GameDto> getInningList() {
		return inningList;
	}
	public void setInningList(List<GameDto> inningList) {
		this.inningList = inningList;
	}
	public String getGbSeq() {
		return gbSeq;
	}
	public void setGbSeq(String gbSeq) {
		this.gbSeq = gbSeq;
	}
	public Integer getGbRuns() {
		return gbRuns;
	}
	public void setGbRuns(Integer gbRuns) {
		this.gbRuns = gbRuns;
	}
	public Integer getGbHits() {
		return gbHits;
	}
	public void setGbHits(Integer gbHits) {
		this.gbHits = gbHits;
	}
	public Integer getGbErrors() {
		return gbErrors;
	}
	public void setGbErrors(Integer gbErrors) {
		this.gbErrors = gbErrors;
	}
	public Integer getGbLob() {
		return gbLob;
	}
	public void setGbLob(Integer gbLob) {
		this.gbLob = gbLob;
	}
	public Integer getGbDp() {
		return gbDp;
	}
	public void setGbDp(Integer gbDp) {
		this.gbDp = gbDp;
	}
	public Integer getGbTrip() {
		return gbTrip;
	}
	public void setGbTrip(Integer gbTrip) {
		this.gbTrip = gbTrip;
	}
	public double getGbAvg() {
		return gbAvg;
	}
	public void setGbAvg(double gbAvg) {
		this.gbAvg = gbAvg;
	}
	public double getGbObp() {
		return gbObp;
	}
	public void setGbObp(double gbObp) {
		this.gbObp = gbObp;
	}
	public double getGbSlg() {
		return gbSlg;
	}
	public void setGbSlg(double gbSlg) {
		this.gbSlg = gbSlg;
	}
	public double getGbOps() {
		return gbOps;
	}
	public void setGbOps(double gbOps) {
		this.gbOps = gbOps;
	}
	public Integer getGbSteal() {
		return gbSteal;
	}
	public void setGbSteal(Integer gbSteal) {
		this.gbSteal = gbSteal;
	}
	public Integer getGbCauSteal() {
		return gbCauSteal;
	}
	public void setGbCauSteal(Integer gbCauSteal) {
		this.gbCauSteal = gbCauSteal;
	}
	public Integer getGbSacBun() {
		return gbSacBun;
	}
	public void setGbSacBun(Integer gbSacBun) {
		this.gbSacBun = gbSacBun;
	}
	public Integer getGbSacFly() {
		return gbSacFly;
	}
	public void setGbSacFly(Integer gbSacFly) {
		this.gbSacFly = gbSacFly;
	}
	public Integer getGbRbi() {
		return gbRbi;
	}
	public void setGbRbi(Integer gbRbi) {
		this.gbRbi = gbRbi;
	}
	public String getGbRegTime() {
		return gbRegTime;
	}
	public void setGbRegTime(String gbRegTime) {
		this.gbRegTime = gbRegTime;
	}
	public String getGbModTime() {
		return gbModTime;
	}
	public void setGbModTime(String gbModTime) {
		this.gbModTime = gbModTime;
	}
	public String getTeam_tmSeq() {
		return team_tmSeq;
	}
	public void setTeam_tmSeq(String team_tmSeq) {
		this.team_tmSeq = team_tmSeq;
	}
	public String getGtSeq() {
		return gtSeq;
	}
	public void setGtSeq(String gtSeq) {
		this.gtSeq = gtSeq;
	}
	public Integer getGtAttendance() {
		return gtAttendance;
	}
	public void setGtAttendance(Integer gtAttendance) {
		this.gtAttendance = gtAttendance;
	}
	public String getGtWeather() {
		return gtWeather;
	}
	public void setGtWeather(String gtWeather) {
		this.gtWeather = gtWeather;
	}
	public String getGtWind() {
		return gtWind;
	}
	public void setGtWind(String gtWind) {
		this.gtWind = gtWind;
	}
	public String getGtUmpireHome() {
		return gtUmpireHome;
	}
	public void setGtUmpireHome(String gtUmpireHome) {
		this.gtUmpireHome = gtUmpireHome;
	}
	public String getGtUmpireFirst() {
		return gtUmpireFirst;
	}
	public void setGtUmpireFirst(String gtUmpireFirst) {
		this.gtUmpireFirst = gtUmpireFirst;
	}
	public String getGtUmpireSecond() {
		return gtUmpireSecond;
	}
	public void setGtUmpireSecond(String gtUmpireSecond) {
		this.gtUmpireSecond = gtUmpireSecond;
	}
	public String getGtUmpireThird() {
		return gtUmpireThird;
	}
	public void setGtUmpireThird(String gtUmpireThird) {
		this.gtUmpireThird = gtUmpireThird;
	}
	public String getGtUmpireLeft() {
		return gtUmpireLeft;
	}
	public void setGtUmpireLeft(String gtUmpireLeft) {
		this.gtUmpireLeft = gtUmpireLeft;
	}
	public String getGtUmpireRight() {
		return gtUmpireRight;
	}
	public void setGtUmpireRight(String gtUmpireRight) {
		this.gtUmpireRight = gtUmpireRight;
	}
	public String getGtRegTime() {
		return gtRegTime;
	}
	public void setGtRegTime(String gtRegTime) {
		this.gtRegTime = gtRegTime;
	}
	public String getGtModTime() {
		return gtModTime;
	}
	public void setGtModTime(String gtModTime) {
		this.gtModTime = gtModTime;
	}
	public String getPbSeq() {
		return pbSeq;
	}
	public void setPbSeq(String pbSeq) {
		this.pbSeq = pbSeq;
	}
	public Integer getPbIsStarter() {
		return pbIsStarter;
	}
	public void setPbIsStarter(Integer pbIsStarter) {
		this.pbIsStarter = pbIsStarter;
	}
	public Integer getPbBattingOrder() {
		return pbBattingOrder;
	}
	public void setPbBattingOrder(Integer pbBattingOrder) {
		this.pbBattingOrder = pbBattingOrder;
	}
	public Integer getPbAb() {
		return pbAb;
	}
	public void setPbAb(Integer pbAb) {
		this.pbAb = pbAb;
	}
	public Integer getPbRuns() {
		return pbRuns;
	}
	public void setPbRuns(Integer pbRuns) {
		this.pbRuns = pbRuns;
	}
	public Integer getPbHits() {
		return pbHits;
	}
	public void setPbHits(Integer pbHits) {
		this.pbHits = pbHits;
	}
	public Integer getPbRbi() {
		return pbRbi;
	}
	public void setPbRbi(Integer pbRbi) {
		this.pbRbi = pbRbi;
	}
	public Integer getPbBb() {
		return pbBb;
	}
	public void setPbBb(Integer pbBb) {
		this.pbBb = pbBb;
	}
	public Integer getPbSo() {
		return pbSo;
	}
	public void setPbSo(Integer pbSo) {
		this.pbSo = pbSo;
	}
	public Integer getPbHomerun() {
		return pbHomerun;
	}
	public void setPbHomerun(Integer pbHomerun) {
		this.pbHomerun = pbHomerun;
	}
	public Integer getPbDouble() {
		return pbDouble;
	}
	public void setPbDouble(Integer pbDouble) {
		this.pbDouble = pbDouble;
	}
	public Integer getPbTriple() {
		return pbTriple;
	}
	public void setPbTriple(Integer pbTriple) {
		this.pbTriple = pbTriple;
	}
	public Integer getPbSteal() {
		return pbSteal;
	}
	public void setPbSteal(Integer pbSteal) {
		this.pbSteal = pbSteal;
	}
	public Integer getPbCauSteal() {
		return pbCauSteal;
	}
	public void setPbCauSteal(Integer pbCauSteal) {
		this.pbCauSteal = pbCauSteal;
	}
	public Integer getPbSacBunt() {
		return pbSacBunt;
	}
	public void setPbSacBunt(Integer pbSacBunt) {
		this.pbSacBunt = pbSacBunt;
	}
	public Integer getPbSacFly() {
		return pbSacFly;
	}
	public void setPbSacFly(Integer pbSacFly) {
		this.pbSacFly = pbSacFly;
	}
	public double getPbAvg() {
		return pbAvg;
	}
	public void setPbAvg(double pbAvg) {
		this.pbAvg = pbAvg;
	}
	public String getPbRegTime() {
		return pbRegTime;
	}
	public void setPbRegTime(String pbRegTime) {
		this.pbRegTime = pbRegTime;
	}
	public String getPbModTime() {
		return pbModTime;
	}
	public void setPbModTime(String pbModTime) {
		this.pbModTime = pbModTime;
	}
	public String getPlayer_pySeq() {
		return player_pySeq;
	}
	public void setPlayer_pySeq(String player_pySeq) {
		this.player_pySeq = player_pySeq;
	}
	public String getPpSeq() {
		return ppSeq;
	}
	public void setPpSeq(String ppSeq) {
		this.ppSeq = ppSeq;
	}
	public Integer getPpIsStarter() {
		return ppIsStarter;
	}
	public void setPpIsStarter(Integer ppIsStarter) {
		this.ppIsStarter = ppIsStarter;
	}
	public double getPpIp() {
		return ppIp;
	}
	public void setPpIp(double ppIp) {
		this.ppIp = ppIp;
	}
	public Integer getPpHit() {
		return ppHit;
	}
	public void setPpHit(Integer ppHit) {
		this.ppHit = ppHit;
	}
	public Integer getPpRun() {
		return ppRun;
	}
	public void setPpRun(Integer ppRun) {
		this.ppRun = ppRun;
	}
	public Integer getPpEaruns() {
		return ppEaruns;
	}
	public void setPpEaruns(Integer ppEaruns) {
		this.ppEaruns = ppEaruns;
	}
	public Integer getPpBb() {
		return ppBb;
	}
	public void setPpBb(Integer ppBb) {
		this.ppBb = ppBb;
	}
	public Integer getPpSo() {
		return ppSo;
	}
	public void setPpSo(Integer ppSo) {
		this.ppSo = ppSo;
	}
	public Integer getPpHr() {
		return ppHr;
	}
	public void setPpHr(Integer ppHr) {
		this.ppHr = ppHr;
	}
	public Integer getPpPitchCount() {
		return ppPitchCount;
	}
	public void setPpPitchCount(Integer ppPitchCount) {
		this.ppPitchCount = ppPitchCount;
	}
	public Integer getPpStrike() {
		return ppStrike;
	}
	public void setPpStrike(Integer ppStrike) {
		this.ppStrike = ppStrike;
	}
	public double getPpEra() {
		return ppEra;
	}
	public void setPpEra(double ppEra) {
		this.ppEra = ppEra;
	}
	public String getPpRegTime() {
		return ppRegTime;
	}
	public void setPpRegTime(String ppRegTime) {
		this.ppRegTime = ppRegTime;
	}
	public String getPpModtime() {
		return ppModtime;
	}
	public void setPpModtime(String ppModtime) {
		this.ppModtime = ppModtime;
	}
	public String getPdSeq() {
		return pdSeq;
	}
	public void setPdSeq(String pdSeq) {
		this.pdSeq = pdSeq;
	}
	public String getPdPosition() {
		return pdPosition;
	}
	public void setPdPosition(String pdPosition) {
		this.pdPosition = pdPosition;
	}
	public Integer getPdError() {
		return pdError;
	}
	public void setPdError(Integer pdError) {
		this.pdError = pdError;
	}
	public String getPrSeq() {
		return prSeq;
	}
	public void setPrSeq(String prSeq) {
		this.prSeq = prSeq;
	}
	public String getPrResult() {
		return prResult;
	}
	public void setPrResult(String prResult) {
		this.prResult = prResult;
	}
	public String getPrRegTime() {
		return prRegTime;
	}
	public void setPrRegTime(String prRegTime) {
		this.prRegTime = prRegTime;
	}
	public String getPrModTime() {
		return prModTime;
	}
	public void setPrModTime(String prModTime) {
		this.prModTime = prModTime;
	}
	public String getPsSeq() {
		return psSeq;
	}
	public void setPsSeq(String psSeq) {
		this.psSeq = psSeq;
	}
	public String getPsPosition() {
		return psPosition;
	}
	public void setPsPosition(String psPosition) {
		this.psPosition = psPosition;
	}
	public Integer getPsInning() {
		return psInning;
	}
	public void setPsInning(Integer psInning) {
		this.psInning = psInning;
	}
	public Integer getPsBattingOrder() {
		return psBattingOrder;
	}
	public void setPsBattingOrder(Integer psBattingOrder) {
		this.psBattingOrder = psBattingOrder;
	}
	public String getPsSubType() {
		return psSubType;
	}
	public void setPsSubType(String psSubType) {
		this.psSubType = psSubType;
	}
	public String getPsRegTime() {
		return psRegTime;
	}
	public void setPsRegTime(String psRegTime) {
		this.psRegTime = psRegTime;
	}
	public String getPsModTime() {
		return psModTime;
	}
	public void setPsModTime(String psModTime) {
		this.psModTime = psModTime;
	}
	public String getPlayerOut_pySeq() {
		return playerOut_pySeq;
	}
	public void setPlayerOut_pySeq(String playerOut_pySeq) {
		this.playerOut_pySeq = playerOut_pySeq;
	}
	public String getPlayerIn_pySeq() {
		return playerIn_pySeq;
	}
	public void setPlayerIn_pySeq(String playerIn_pySeq) {
		this.playerIn_pySeq = playerIn_pySeq;
	}
	
}
