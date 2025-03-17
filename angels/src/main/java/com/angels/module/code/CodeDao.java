package com.angels.module.code;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CodeDao {
	
	public List<CodeDto> selectList(CodeVo codeVo);
	public int insert(CodeDto codeDto);
	public int selectOneCount();
}
