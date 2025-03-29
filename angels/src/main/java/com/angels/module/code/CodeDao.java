package com.angels.module.code;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CodeDao {
	
	public List<CodeDto> selectList(CodeVo codeVo);
	public int insert(CodeDto codeDto);
	public int selectOneCount(CodeVo codeVo);
	public List<CodeDto> listCodeGroup(CodeVo codeVo);
	public List<CodeDto> selectListCachedCodeArrayList();
	public int update(CodeDto codeDto);
	public CodeDto selectOne(CodeDto codeDto);
	public int uelete(CodeDto codeDto);
	public int delete(CodeDto codeDto);
}
