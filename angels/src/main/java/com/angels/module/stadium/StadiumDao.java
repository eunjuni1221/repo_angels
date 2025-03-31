package com.angels.module.stadium;

import java.util.List;

public interface StadiumDao {
	
	public List<StadiumDto> selectList(StadiumVo stadiumVo);
	public int selectOneCount(StadiumVo stadiumVo);
	public int insert(StadiumDto stadiumDto);
	public List<StadiumDto> selectTeam();
	public StadiumDto selectOne(StadiumDto stadiumDto);
	public int update (StadiumDto stadiumDto);
	public int uelete (StadiumDto stadiumDto);
	public int delete (StadiumDto stadiumDto);
}
