package com.angels.module.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.angels.module.Base.BaseService;

@Service
public class PlayerService extends BaseService {
	
	@Autowired
	PlayerDao playerDao;
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	public List<PlayerDto> selectList(PlayerVo playerVo) {
		return playerDao.selectList(playerVo);
	}
	public List<PlayerDto> selectTeam() {
		return playerDao.selectTeam();
	}
	public int selectOneCount(PlayerVo playerVo) {
		return playerDao.selectOneCount(playerVo);
	}
	public int insert(PlayerDto playerDto) throws Exception {
		playerDao.insert(playerDto);
		uploadFilesToS3(
				playerDto.getUploadImg1()
    			, playerDto
    			, "applGoodsUploaded"
    			, playerDto.getUploadImg1Type()
    			, playerDto.getUploadImg1MaxNumber()
    			, playerDto.getPySeq()
    			, playerDao
    			, amazonS3Client);
		return 1;
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
