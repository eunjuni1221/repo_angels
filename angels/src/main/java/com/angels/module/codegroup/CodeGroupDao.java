package com.angels.module.codegroup;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CodeGroupDao {

	public List<CodeGroupDto> selectList(CodeGroupVo codeGroupVo);
	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto);
	public int insert (CodeGroupDto codeGroupDto);
	public int selectOneCount();	
}
