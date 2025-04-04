package com.angels.module.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.angels.module.Base.BaseController;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController extends BaseController{

	@Autowired
	UserService userService;

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
	public String userHofInst(UserDto dto) {

		userService.insert(dto);
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
		
		if(rtUserDto != null) {
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
		
		if(rtUserDto != null) {
			returnMap.put("rt", "success");
			httpSession.setAttribute("sessXdmSeq", rtUserDto.getUrSeq());
			httpSession.setAttribute("sessXdmName", rtUserDto.getUrNickname());
			System.out.println(httpSession.getAttribute("sessXdmName"));

			httpSession.setAttribute("sessXdmId", rtUserDto.getUrID());
			
		} else {
			returnMap.put("rt", "false");
		}		
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/login/signoutHofProc")
	public Map<String, Object> signoutHofProc(UserDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();

			httpSession.setAttribute("sessXdmSeq", null);
			httpSession.setAttribute("sessXdmName", null);
			httpSession.setAttribute("sessXdmId", null);		

			returnMap.put("rt", "success");				
		return returnMap;
	}
	
}
