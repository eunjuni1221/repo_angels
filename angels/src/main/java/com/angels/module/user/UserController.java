package com.angels.module.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/user/UserXdmList")
	public String userXdmList(Model model, UserVo vo, UserDto dto) throws Exception {
		System.out.println(vo.getThisPage());
		 vo.setParamsPaging(userService.selectOneCount(vo));
		 
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
	

}
