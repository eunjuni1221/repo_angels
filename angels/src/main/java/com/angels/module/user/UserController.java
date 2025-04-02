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

	@RequestMapping(value = "/user/UserXdmForm")
	public String userXdmForm(@ModelAttribute("vo") Model model, UserVo vo, UserDto dto) throws Exception {

		if (vo.getUrSeq().equals("0") || vo.getUrSeq().equals("")) {
//			insert mode
		} else {
//			update mode
			model.addAttribute("item", userService.selectOne(dto));
		}

		model.addAttribute("vo", vo);
		return "xdm/user/UserXdmForm";
	}

	@RequestMapping(value = "/user/UserXdmInst")
	public String userXdmInst(UserDto dto) {

		userService.insert(dto);
		return "redirect:UserXdmList";
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
	
}
