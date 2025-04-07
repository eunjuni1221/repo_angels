package com.angels.module.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.angels.module.team.TeamDto;

@Repository
public interface UserDao {
	
	public List<UserDto> selectList(UserVo userVo);
	public UserDto selectOne(UserDto userDto);
	public int insert (UserDto userDto);
	public int selectOneCount(UserVo userVo);
	public int update (UserDto userDto);
	public int uelete(UserDto userDto);
	public UserDto selectOneLogin(UserDto userDto);
	public List<UserDto> sex();
	public List<UserDto> telecom();
	public List<UserDto> email();
	public int idCheck(UserDto userDto);
	public int nicknameCheck(UserDto userDto);
	public int updateID(UserDto userDto);
	public int updatePassword(UserDto userDto);
	public int updateEmail(UserDto userDto);
	public int updatePhoneNumber(UserDto userDto);
	public int passwordCheck(UserDto userDto);
	public int phoneNumberCheck(UserDto userDto);
	public int telecomCheck(UserDto userDto);



}
