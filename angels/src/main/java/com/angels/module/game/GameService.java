package com.angels.module.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
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
	public List<GameDto> listInstForLineScore(GameDto gameDto) {
		return gameDao.listInstForLineScore(gameDto);
	}
	public int insertInningScore(GameDto gameDto) {
		return gameDao.insertInningScore(gameDto);
	}
	public List<GameDto> selectInningByGameSeq(GameDto gameDto) {
		return gameDao.selectInningByGameSeq(gameDto);
	}
	public List<GameDto> selectHofScoreList(GameDto gameDto) {
		return gameDao.selectHofScoreList(gameDto);
	}
	public List<GameDto> selectOneInningByGameSeq(GameDto gameDto) {
		return gameDao.selectOneInningByGameSeq(gameDto);
	}
	public GameDto selectOneHofScore(GameDto gameDto) {
		return gameDao.selectOneHofScore(gameDto);
	}
	public int insertBoxScore(GameDto gameDto) {
		return gameDao.insertBoxScore(gameDto);
	}
	public int insertGameMeta(GameDto gameDto) {
		return gameDao.insertGameMeta(gameDto);
	}
	public int insertPlayerBatting(GameDto gameDto) {
		return gameDao.insertPlayerBatting(gameDto);
	}
	public int insertPlayerPitching(GameDto gameDto) {
		return gameDao.insertPlayerPitching(gameDto);
	}
	public int insertPlayerDefense(GameDto gameDto) {
		return gameDao.insertPlayerDefense(gameDto);
	}
	public int insertPitchingResult(GameDto gameDto) {
		return gameDao.insertPitchingResult(gameDto);
	}
	public int insertPlayerSub(GameDto gameDto) {
		return gameDao.insertPlayerSub(gameDto);
	}
}
