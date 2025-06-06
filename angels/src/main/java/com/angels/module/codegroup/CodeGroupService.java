package com.angels.module.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CodeGroupService {
	
	@Autowired
	CodeGroupDao codeGroupDao;
	
	public List<CodeGroupDto> selectList(CodeGroupVo codeGroupVo) {
		return codeGroupDao.selectList(codeGroupVo);
	}
	
	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto) {
		return codeGroupDao.selectOne(codeGroupDto);
	}
	
	public int insert (CodeGroupDto codeGroupDto) {
		return codeGroupDao.insert(codeGroupDto);
	}
	public int selectOneCount(CodeGroupVo codeGroupVo) {
		return codeGroupDao.selectOneCount(codeGroupVo);
	}
	public int update (CodeGroupDto codeGroupDto) {
		return codeGroupDao.update(codeGroupDto);
	}
	public int uelete(CodeGroupDto codeGroupDto) {
		return codeGroupDao.uelete(codeGroupDto);
	}
	public int delete(CodeGroupDto codeGroupDto) {
		return codeGroupDao.delete(codeGroupDto);
	}

}
