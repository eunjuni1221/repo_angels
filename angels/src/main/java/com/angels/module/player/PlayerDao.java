package com.angels.module.player;

import java.util.List;

import com.angels.module.Base.BaseDao;
import com.angels.module.Base.BaseDto;

public interface PlayerDao extends BaseDao{

	public List<PlayerDto> selectList(PlayerVo playerVo);
	public List<PlayerDto> selectHofList(PlayerVo playerVo);
	public List<PlayerDto> selectTeam();
	public int selectOneCount(PlayerVo playerVo);
	public int insert(PlayerDto playerDto);
	public List<PlayerDto> selectPosition();
	public List<PlayerDto> selectThrowHand();
	public List<PlayerDto> selectBatHand();
	public List<PlayerDto> selectNationality();
	public List<PlayerDto> selectStatus();
	public List<PlayerDto> selectListCachedCodeArrayList();
	public PlayerDto selectOne(PlayerDto playerDto);
	public int uelete(PlayerDto playerDto);
	public int delete(PlayerDto playerDto);
	public int update(PlayerDto playerDto);
	public int insertUploaded(BaseDto dto);

}
