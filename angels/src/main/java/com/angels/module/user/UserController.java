package com.angels.module.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.angels.module.Base.BaseController;
import com.angels.module.mail.MailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController extends BaseController{

	@Autowired
	UserService userService;
	@Autowired
	MailService mailService;

	@RequestMapping(value = "/user/UserXdmList")
	public String userXdmList(Model model, UserVo vo, UserDto dto) throws Exception {
		setSearch(vo);
		
		vo.setParamsPaging(userService.selectOneCount(vo));
		System.out.println(vo.getShEmail());

		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", userService.selectList(vo));
		}

		model.addAttribute("vo", vo);
		return "xdm/user/UserXdmList";
	}


	@RequestMapping(value = "/hof/hofRegAgree")
	public String userHofRegAgree() {
		return "hof/user/baseball-registerAgree";
	}
	
	@RequestMapping(value = "/hof/hofRegister")
	public String userHofRegister(Model model) {
		model.addAttribute("telecom",userService.telecom());
		model.addAttribute("email",userService.email());
		model.addAttribute("sex",userService.sex());
		return "hof/user/baseball-register";
	}
	
	@RequestMapping(value = "/user/UserHofInst")
	public String userHofInst(UserDto dto) throws Exception {
		
		dto.setUrPassword(encodeBcrypt(dto.getUrPassword(), 10));
		
		userService.insert(dto);
		
		mailService.sendMailWelcome();
		
		System.out.println(dto.getUrSeq());
		System.out.println(dto.getUrDelNy());
		System.out.println(dto.getUrName());
		System.out.println(dto.getUrGender());
		System.out.println(dto.getTelecom());
		System.out.println(dto.getPhoneNumber());
		System.out.println(dto.getUrBirth());
		System.out.println(dto.getUrID());
		System.out.println(dto.getEmailID());
		System.out.println(dto.getEmail());
		System.out.println(dto.getUrPassword());
		System.out.println(dto.getUrRegTime());

		return "redirect:/hof/hofLogin";
	}

	@RequestMapping(value = "/user/UserXdmUpdt")
	public String userXdmUpdt(UserDto dto) {
		System.out.println(dto.getUrSeq());
		userService.update(dto);
		return "redirect:UserXdmList";
	}

	@RequestMapping(value = "/user/UserXdmUele")
	public String userXdmUele(UserDto dto) {
		userService.uelete(dto);
		return "redirect:UserXdmList";
	}

	@ResponseBody
	@RequestMapping(value = "/login/signinXdmProc")
	public Map<String, Object> signinXdmProc(UserDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		UserDto rtUserDto = userService.selectOneLogin(dto);
//		아이디, 패스워드 집어넣어서 데이터 받기
		
		if(rtUserDto != null && matchesBcrypt(dto.getUrPassword(), rtUserDto.getUrPassword(), 10)) {
			returnMap.put("rt", "success");
			httpSession.setAttribute("sessXdmSeq", rtUserDto.getUrSeq());
			httpSession.setAttribute("sessXdmName", rtUserDto.getUrName());
			httpSession.setAttribute("sessXdmId", rtUserDto.getUrID());
			
		} else {
			returnMap.put("rt", "false");
		}		
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/login/signoutXdmProc")
	public Map<String, Object> signoutXdmProc(UserDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();

			httpSession.setAttribute("sessXdmSeq", null);
			httpSession.setAttribute("sessXdmName", null);
			httpSession.setAttribute("sessXdmId", null);		

			returnMap.put("rt", "success");				
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/login/signinHofProc")
	public Map<String, Object> signinHofProc(UserDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		UserDto rtUserDto = userService.selectOneLogin(dto);
//		아이디, 패스워드 집어넣어서 데이터 받기
		
		if(rtUserDto != null && matchesBcrypt(dto.getUrPassword(), rtUserDto.getUrPassword(), 10)) {
			returnMap.put("rt", "success");
			httpSession.setAttribute("sessHofSeq", rtUserDto.getUrSeq());
			httpSession.setAttribute("sessHofName", rtUserDto.getUrName());
			httpSession.setAttribute("sessHofNickname", rtUserDto.getUrNickname());
			httpSession.setAttribute("sessHofId", rtUserDto.getUrID());
						
		} else {
			returnMap.put("rt", "false");
		}		
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/login/signoutHofProc")
	public Map<String, Object> signoutHofProc(UserDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();

			httpSession.setAttribute("sessHofSeq", null);
			httpSession.setAttribute("sessHofName", null);
			httpSession.setAttribute("sessHofNickname", null);
			httpSession.setAttribute("sessHofId", null);		


			returnMap.put("rt", "success");				
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/login/idNmCheckXdmProc")
	public Map<String, Object> idNmCheckXdmProc(UserDto dto) throws Exception {
	    Map<String, Object> returnMap = new HashMap<>();

	    // ID 중복 체크
	    if (dto.getUrID() != null && !dto.getUrID().trim().isEmpty()) {
	        int idCount = userService.idCheck(dto);
	        returnMap.put("idExists", idCount > 0);  // true: 중복, false: 사용 가능
	    }

	    // 닉네임 중복 체크
	    if (dto.getUrNickname() != null && !dto.getUrNickname().trim().isEmpty()) {
	        int nicknameCount = userService.nicknameCheck(dto);
	        returnMap.put("nicknameExists", nicknameCount > 0);
	    }

	    return returnMap;
	}
	
	@RequestMapping(value = "/hof/hofUsrProfile")
	public String hofUsrProfile(HttpSession httpSession, UserDto dto, Model model) {
		dto.setUrSeq((String) httpSession.getAttribute("sessHofSeq"));
		model.addAttribute("list", userService.selectOne(dto));
		return "hof/user/baseball-profile";
	}
	
	@RequestMapping(value = "/hof/hofUsrChangePassword")
	public String hofUsrChangePassword() {
		
		return "hof/user/baseball_profile-changePassword";
	}
	
	@ResponseBody
	@RequestMapping(value = "/hof/hofUsrChangePasswordProc")
	public Map<String, Object> hofUsrChangePassword(UserDto dto, HttpSession session) {
	    Map<String, Object> returnMap = new HashMap<>();

	    String sessSeq = (String) session.getAttribute("sessHofSeq");

	    if (sessSeq == null) {
	        returnMap.put("rt", "sessionExpired");
	        return returnMap;
	    }

	    dto.setUrSeq(sessSeq);
	    UserDto currentUser = userService.selectOne(dto);

	    if (currentUser == null) {
	        returnMap.put("rt", "userNotFound");
	        return returnMap;
	    }

	    if (!matchesBcrypt(dto.getUrPassword(), currentUser.getUrPassword(), 10)) {
	        returnMap.put("rt", "wrongCurrentPassword");
	        return returnMap;
	    }

	    if (dto.getUrNewPassword() == null || dto.getUrNewPassword().trim().isEmpty()) {
	        returnMap.put("rt", "newPasswordEmpty");
	        return returnMap;
	    }

	    String newEncodedPassword = encodeBcrypt(dto.getUrNewPassword(), 10);
	    dto.setUrPassword(newEncodedPassword);
	    userService.updatePassword(dto);

	    returnMap.put("rt", "success");
	    return returnMap;
	}
	
	@RequestMapping(value = "/hof/hofUsrChangeEmail")
	public String hofUsrChangeEmail() {
		
		return "hof/user/baseball_profile-changeEmail";
	}
	
	@RequestMapping(value = "/hof/hofUsrChangePhoneNumber")
	public String hofUsrChangePhoneNumber() {
		
		return "hof/user/baseball_profile-changePhoneNumber";
	}
	
	@RequestMapping(value = "/hof/hofUsrChangeAddress")
	public String hofUsrChangeAddress() {
		
		return "hof/user/hofUsrChangeAddress";
	}
	
	@ResponseBody
	@RequestMapping(value = "/hof/hofUsrChangeAddressProc")
	public Map<String, Object> hofUsrChangeAddress(UserDto dto, HttpSession session) {
	    Map<String, Object> returnMap = new HashMap<>();

	    String sessSeq = (String) session.getAttribute("sessHofSeq");

	    if (sessSeq == null) {
	        returnMap.put("rt", "sessionExpired");
	        return returnMap;
	    }

	    dto.setUrSeq(sessSeq);
	    UserDto currentUser = userService.selectOne(dto);

	    if (currentUser == null) {
	        returnMap.put("rt", "userNotFound");
	        return returnMap;
	    }

	    if (!matchesBcrypt(dto.getUrPassword(), currentUser.getUrPassword(), 10)) {
	        returnMap.put("rt", "wrongCurrentPassword");
	        return returnMap;
	    }

	    if (dto.getUrNewPassword() == null || dto.getUrNewPassword().trim().isEmpty()) {
	        returnMap.put("rt", "newPasswordEmpty");
	        return returnMap;
	    }

	    String newEncodedPassword = encodeBcrypt(dto.getUrNewPassword(), 10);
	    dto.setUrPassword(newEncodedPassword);
	    userService.updatePassword(dto);

	    returnMap.put("rt", "success");
	    return returnMap;
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/login/passwordCheckXdmProc")
//	public Map<String, Object> passwordCheckXdmProc(UserDto dto) throws Exception {
//	    Map<String, Object> returnMap = new HashMap<>();
//
//	    // ID 중복 체크
//	    if (dto.getUrPassword() != null && !dto.getUrPassword().trim().isEmpty()) {
//	        int passwordCount = userService.idCheck(dto);
//	        returnMap.put("passwordExists", passwordCount > 0);  // true: 중복, false: 사용 가능
//	    }
//	    return returnMap;
//	}
	
//	@ResponseBody
//	@RequestMapping(value = "/login/phoneCheckXdmProc")
//	public Map<String, Object> phoneCheckXdmProc(UserDto dto) throws Exception {
//	    Map<String, Object> returnMap = new HashMap<>();
//
//	    // 폰 번호 중복 체크
//	    if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().trim().isEmpty()) {
//	        int phoneNumberCount = userService.phoneNumberCheck(dto);
//	        returnMap.put("phoneNumberExists", phoneNumberCount > 0);  // true: 중복, false: 사용 가능
//	    }
//	    
//	    // 통신사 중복 체크
//	    if (dto.getTelecom() != null && !dto.getTelecom().trim().isEmpty()) {
//	        int telecomCount = userService.telecomCheck(dto);
//	        returnMap.put("nicknameExists", telecomCount > 0);
//	    }
//
//	    
//	    return returnMap;
//	}
	
}
