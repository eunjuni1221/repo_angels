package com.angels.module.stadium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angels.module.team.TeamDto;
import com.angels.module.team.TeamVo;

@Controller
public class StadiumController {
	
	@Autowired
	StadiumService stadiumService;
	
	@RequestMapping(value="/stadium/StadiumXdmList")
	public String stadiumXdmList(Model model, StadiumVo vo) throws Exception {
		System.out.println(vo.getThisPage());
		vo.setParamsPaging(stadiumService.selectOneCount(vo));
		model.addAttribute("selectTeam",stadiumService.selectTeam());

		
		if(vo.getTotalRows() > 0) {
		model.addAttribute("list", stadiumService.selectList(vo));
		}
		model.addAttribute("vo", vo);
		return "xdm/stadium/StadiumXdmList";
	}
	@RequestMapping(value = "/stadium/StadiumXdmForm")
	public String teamXdmForm(Model model) {
		model.addAttribute("selectTeam",stadiumService.selectTeam());
	return "xdm/stadium/StadiumXdmForm"; 
	}
	
	@RequestMapping(value = "/stadium/StadiumXdmInst")
	public String teamXdmInst(StadiumDto stadiumDto) {
		stadiumService.insert(stadiumDto);
		
		return "redirect:StadiumXdmList";
	}
}
