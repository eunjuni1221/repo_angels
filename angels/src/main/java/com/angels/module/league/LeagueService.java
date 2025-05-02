package com.angels.module.league;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueService {
	
	@Autowired
	LeagueDao leagueDao;
	
	public List<LeagueDto> selectList(LeagueVo leagueVo) {
		return leagueDao.selectList(leagueVo);
	}
	
	public int insert(LeagueDto leaguedto) {
		return leagueDao.insert(leaguedto);
	}
	
	public int selectOneCount(LeagueVo leagueVo) {
		return leagueDao.selectOneCount(leagueVo);
	}

	public boolean exists(String lgSeq) {
	    return leagueDao.exists(lgSeq) > 0;
	}
}
