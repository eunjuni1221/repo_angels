package com.angels.module.player;

import java.util.List;

import com.angels.module.team.TeamDto;

public interface PlayerDao {

	public List<PlayerDto> selectList(PlayerVo playerVo);
	public List<PlayerDto> selectTeam();
	public int selectOneCount();
	public int insert(PlayerDto playerDto);
	public List<PlayerDto> selectPosition();
	public List<PlayerDto> selectThrowHand();
	public List<PlayerDto> selectBatHand();
	public List<PlayerDto> selectNationality();
	public List<PlayerDto> selectStatus();
	public List<PlayerDto> selectListCachedCodeArrayList();

}
