package com.angels.module.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angels.module.team.TeamDto;

@Service
public class PlayerService {
	
	@Autowired
	PlayerDao playerDao;
	
	public List<PlayerDto> selectList(PlayerVo playerVo) {
		return playerDao.selectList(playerVo);
	}
	public List<PlayerDto> selectTeam() {
		return playerDao.selectTeam();
	}
	public int selectOneCount(PlayerVo playerVo) {
		return playerDao.selectOneCount(playerVo);
	}
	public int insert(PlayerDto playerDto) {
		return playerDao.insert(playerDto);
	}
	public List<PlayerDto> selectPosition(){
		return playerDao.selectPosition();
	}
	public List<PlayerDto> selectThrowHand() {
		return playerDao.selectThrowHand();
	}
	public List<PlayerDto> selectBatHand(){
		return playerDao.selectBatHand();
	}
	public List<PlayerDto> selectNationality() {
		return playerDao.selectNationality();
	}
	public List<PlayerDto> selectStatus(){
		return playerDao.selectStatus();
	}
	public PlayerDto selectOne(PlayerDto playerDto) {
		return playerDao.selectOne(playerDto);
	}
	public int update(PlayerDto playerDto) {
		return playerDao.update(playerDto);
	}
	public int uelete(PlayerDto playerDto) {
		return playerDao.uelete(playerDto);
	}
	public int delete(PlayerDto playerDto) {
		return playerDao.delete(playerDto);
	}
}
