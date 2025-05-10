package com.angels.module.game;

import java.util.List;

public interface GameDao {

	public List<GameDto> selectList(GameVo gameVo);
	public int selectOneCount(GameVo gameVo);
	public int insert(GameDto gameDto);
}
