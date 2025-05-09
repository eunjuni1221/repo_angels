package com.angels.module.game;

import java.util.List;

public interface GameDao {

	public List<GameDto> selectList(GameVo gameVo);

}
