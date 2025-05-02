package com.angels.module.division;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisionService {
	
	@Autowired
	DivisionDao divisionDao;
	
	public List<DivisionDto> selectList(DivisionVo divisionVo) {
		return divisionDao.selectList(divisionVo);
	}
	
	public int insert(DivisionDto divisiondto) {
		return divisionDao.insert(divisiondto);
	}
	
	public int selectOneCount(DivisionVo divisionVo) {
		return divisionDao.selectOneCount(divisionVo);
	}

}
