package com.angels.module.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public List<UserDto> selectList(UserVo userVo) {
		return userDao.selectList(userVo);
	}
	public UserDto selectOne(UserDto userDto) {
		return userDao.selectOne(userDto);
	}
	public int insert (UserDto codeGroupDto) {
		return userDao.insert(codeGroupDto);
	}
	public int selectOneCount(UserVo userVo) {
		return userDao.selectOneCount(userVo);
	}
	public int update (UserDto userDto) {
		return userDao.update(userDto);
	}
	public int uelete(UserDto userDto) {
		return userDao.uelete(userDto);
	}
	public UserDto selectOneLogin(UserDto userDto) {
		return userDao.selectOneLogin(userDto);
	}

}
