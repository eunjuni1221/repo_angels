package com.angels.module.game;

import java.util.List;

public interface GameDao {

	public List<GameDto> selectList(GameVo gameVo);
	public int selectOneCount(GameVo gameVo);
	public int insert(GameDto gameDto);
	public List<GameDto> selectHofList(GameDto gameDto);
	public int insertLineScore(GameDto gameDto);
	public List<GameDto> listInstForLineScore(GameDto gameDto);
	public int insertInningScore(GameDto gameDto);
}
