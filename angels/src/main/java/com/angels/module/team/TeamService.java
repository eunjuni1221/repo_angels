package com.angels.module.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;

@Service
public class TeamService {
	
	@Autowired
	TeamDao teamDao;
	
	
	public List<TeamDto> selectList(TeamVo teamVo) {
		return teamDao.selectList(teamVo);
	}
	public int selectOneCount(TeamVo teamVo) {
		return teamDao.selectOneCount(teamVo);
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
	public int update(TeamDto teamDto) {
		return teamDao.update(teamDto);
	}
	public int uelete(TeamDto teamDto) {
		return teamDao.uelete(teamDto);
	}
	public int delete(TeamDto teamDto) {
		return teamDao.delete(teamDto); 
	}
	public TeamDto selectOne(TeamDto teamDto) {
		return teamDao.selectOne(teamDto);
	}
	public List<TeamDto> selectHofList(){
		return teamDao.selectHofList();
	}
}
