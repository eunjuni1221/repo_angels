package com.angels.module.league;

import java.util.List;

public interface LeagueDao {
	
	public List<LeagueDto> selectList(LeagueVo leagueVo);
	public int insert(LeagueDto leagueDto);
	public int selectOneCount(LeagueVo leagueVo);
	public int exists(String lgSeq);


}
