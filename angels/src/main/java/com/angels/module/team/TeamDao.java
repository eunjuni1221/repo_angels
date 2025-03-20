package com.angels.module.team;

import java.util.List;

public interface TeamDao {
	
	public List<TeamDto> selectList(TeamVo teamVo);
	public int selectOneCount();
	public int insert(TeamDto teamDto);

}
