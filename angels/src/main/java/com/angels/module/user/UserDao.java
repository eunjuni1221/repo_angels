package com.angels.module.user;

import java.util.List;

import org.springframework.stereotype.Repository;



@Repository
public interface UserDao {
	
	public List<UserDto> selectList(UserVo userVo);
	public UserDto selectOne(UserDto userDto);
	public int insert (UserDto userDto);
	public int selectOneCount(UserVo userVo);
	public int update (UserDto userDto);
	public int uelete(UserDto userDto);
	public UserDto selectOneLogin(UserDto userDto);

}
