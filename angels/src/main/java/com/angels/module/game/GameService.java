package com.angels.module.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
	
	@Autowired
	GameDao gameDao;
	
	public List<GameDto> selectList(GameVo gameVo) {
		return gameDao.selectList(gameVo);
	}
	public int selectOneCount(GameVo gameVo) {
		return gameDao.selectOneCount(gameVo);
	}
	public int insert(GameDto gameDto) {
		return gameDao.insert(gameDto);
	}
	public List<GameDto> selectHofList(GameDto gameDto){
		return gameDao.selectHofList(gameDto);
	}
	public int insertLineScore(GameDto gameDto) {
		return gameDao.insertLineScore(gameDto);
	}
}
