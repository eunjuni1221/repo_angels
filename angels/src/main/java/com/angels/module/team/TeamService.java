package com.angels.module.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
	
	@Autowired
	TeamDao teamDao;
	
	public List<TeamDto> selectList(TeamVo teamVo) {
		return teamDao.selectList(teamVo);
	}
	public int selectOneCount() {
		return teamDao.selectOneCount();
	}
	public int insert(TeamDto teamdto) {
		return teamDao.insert(teamdto);
	}
	public List<TeamDto> league(){
		return teamDao.league();
	}
	public List<TeamDto> division() {
		return teamDao.division();
	}

}
