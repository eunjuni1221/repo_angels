package com.angels.module.team;

import java.util.List;

public interface TeamDao {
	
	public List<TeamDto> selectList(TeamVo teamVo);
	public int selectOneCount(TeamVo teamVo);
	public int insert(TeamDto teamDto);
	public List<TeamDto> league();
	public List<TeamDto> division();
	public List<TeamDto> selectListCachedCodeArrayList();
	public int update(TeamDto teamDto);
	public TeamDto selectOne(TeamDto teamDto);
	public int uelete(TeamDto teamDto);
	public int delete(TeamDto teamDto);
	public List<TeamDto> selectHofList();
	

}
