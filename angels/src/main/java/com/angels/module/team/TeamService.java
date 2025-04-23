package com.angels.module.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;

@Service
public class TeamService {
	
	@Autowired
	TeamDao teamDao;
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	public List<TeamDto> selectList(TeamVo teamVo) {
		return teamDao.selectList(teamVo);
	}
	public int selectOneCount(TeamVo teamVo) {
		return teamDao.selectOneCount(teamVo);
	}
	public int insert(TeamDto teamdto) {
		teamDao.insert(teamdto);
//    	uploadFilesToS3(
//    			teamdto.getUploadImg1()
//    			, teamdto
//    			, "infrBannerUploaded"
//    			, teamdto.getUploadImg1Type()
//    			, teamdto.getUploadImg1MaxNumber()
//    			, teamdto.getIfbnSeq()
//    			, teamDao
//    			, amazonS3Client);
    	return 1; 
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
}
