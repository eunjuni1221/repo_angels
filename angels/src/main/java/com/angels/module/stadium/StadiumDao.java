package com.angels.module.stadium;

import java.util.List;

public interface StadiumDao {
	
	public List<StadiumDto> selectList(StadiumVo stadiumVo);
	public int selectOneCount();
	public int insert(StadiumDto stadiumDto);
	public List<StadiumDto> selectTeam();
}
