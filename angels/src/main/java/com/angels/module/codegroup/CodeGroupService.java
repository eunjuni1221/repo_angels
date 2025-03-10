package com.angels.module.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupService {
	
	@Autowired
	CodeGroupDao codeGroupDao;
	
	public List<CodeGroupDto> selectList() {
		return codeGroupDao.selectList();
	}
	
	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto) {
		return codeGroupDao.selectOne(codeGroupDto);
	}
	
	public int insert (CodeGroupDto codeGroupDto) {
		return codeGroupDao.insert(codeGroupDto);
	}

}
