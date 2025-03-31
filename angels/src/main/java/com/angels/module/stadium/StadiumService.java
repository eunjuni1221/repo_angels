package com.angels.module.stadium;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StadiumService {

	@Autowired
	StadiumDao stadiumDao;
	
	public List<StadiumDto> selectList(StadiumVo stadiumVo) {
		return stadiumDao.selectList(stadiumVo);
	}
	public int selectOneCount(StadiumVo stadiumVo) {
		return stadiumDao.selectOneCount(stadiumVo);
	}
	public int insert(StadiumDto stadiumdto) {
		return stadiumDao.insert(stadiumdto);
	}
	public List<StadiumDto> selectTeam() {
		return stadiumDao.selectTeam();
	}
	public StadiumDto selectOne(StadiumDto stadiumDto) {
		return stadiumDao.selectOne(stadiumDto);
	}
	public int update(StadiumDto stadiumDto) {
		return stadiumDao.update(stadiumDto);
	}
	public int uelete(StadiumDto stadiumDto) {
		return stadiumDao.uelete(stadiumDto);
	}
	public int delete(StadiumDto stadiumDto) {
		return stadiumDao.delete(stadiumDto);
	}
}
