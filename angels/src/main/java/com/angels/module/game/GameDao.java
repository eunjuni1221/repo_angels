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
	public List<GameDto> selectInningByGameSeq(GameDto gameDto);
	public List<GameDto> selectHofScoreList(GameDto gameDto);
	public GameDto selectOneHofScore(GameDto gameDto);
	public List<GameDto> selectOneInningByGameSeq(GameDto gameDto);
	public int insertBoxScore(GameDto gameDto);
	public int insertGameMeta (GameDto gameDto);
	public int insertPlayerBatting (GameDto gameDto);
	public int insertPlayerPitching (GameDto gameDto);
	public int insertPlayerDefense (GameDto gameDto);
	public int insertPitchingResult (GameDto gameDto);
	public int insertPlayerSub (GameDto gameDto);
}
