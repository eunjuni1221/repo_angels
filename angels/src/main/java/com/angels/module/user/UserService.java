package com.angels.module.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.angels.module.Base.BaseDto;
import com.angels.module.Base.BaseService;
import com.angels.module.code.CodeDto;



@Service
public class UserService extends BaseService{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	public List<UserDto> selectList(UserVo userVo) {
		return userDao.selectList(userVo);
	}
	public UserDto selectOne(UserDto userDto) {
		return userDao.selectOne(userDto);
	}
	public int insert (UserDto userDto) {
		return userDao.insert(userDto);
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
	public int insertUploaded(UserDto userDto) throws Exception {
		uploadFilesToS3(
				userDto.getUploadImg1()
    			, userDto
    			, "applgoodsuploaded"
    			, userDto.getUploadImg1Type()
    			, userDto.getUploadImg1MaxNumber()
    			, userDto.getUrSeq()
    			, userDao
    			, amazonS3Client);
		return 1;		
	}
	public int selectOneCountImage(UserDto userDto) {
		return userDao.selectOneCountImage(userDto);
	}
}
