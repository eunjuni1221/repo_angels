package com.angels.module.division;

import java.util.List;

public interface DivisionDao {

	public List<DivisionDto> selectList(DivisionVo divisionVo);
	public int insert(DivisionDto divisionDto);
	public int selectOneCount(DivisionVo divisionVo);
}
