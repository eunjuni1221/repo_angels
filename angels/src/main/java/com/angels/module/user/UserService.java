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
	public List<UserDto> sex(){
		return userDao.sex();
	}
	public List<UserDto> email(){
		return userDao.email();
	}
	public List<UserDto> telecom(){
		return userDao.telecom();
	}
	public int idCheck(UserDto userDto) {
		return userDao.idCheck(userDto);
	}
	public int nicknameCheck(UserDto userDto) {
		return userDao.nicknameCheck(userDto);
	}
	public int updateID (UserDto userDto) {
		return userDao.updateID(userDto);
	}
	public int updatePassword (UserDto userDto) {
		return userDao.updatePassword(userDto);
	}
	public int updateEmail (UserDto userDto) {
		return userDao.updateEmail(userDto);
	}
	public int updatePhoneNumber (UserDto userDto) {
		return userDao.updatePhoneNumber(userDto);
	}
	public int updateAddress (UserDto userDto) {
		return userDao.updateAddress(userDto);
	}
	public int passwordCheck(UserDto userDto) {
		return userDao.passwordCheck(userDto);
	}
	public int phoneNumberCheck(UserDto userDto) {
		return userDao.phoneNumberCheck(userDto);
	}
	public int telecomCheck(UserDto userDto) {
		return userDao.telecomCheck(userDto);
	}

}
