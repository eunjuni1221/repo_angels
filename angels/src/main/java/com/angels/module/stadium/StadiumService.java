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
	public int selectOneCount() {
		return stadiumDao.selectOneCount();
	}
	public int insert(StadiumDto stadiumdto) {
		return stadiumDao.insert(stadiumdto);
	}
	public List<StadiumDto> selectTeam() {
		return stadiumDao.selectTeam();
	}
}
