package com.angels.module.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angels.module.code.CodeService;

@Controller
public class TeamController {
	
	@Autowired
	TeamService teamService;
	CodeService codeService;
	
	@RequestMapping(value="/team/TeamXdmList")
	public String teamXdmList(Model model, TeamVo vo) {
		vo.setParamsPaging(teamService.selectOneCount());
		model.addAttribute("list", teamService.selectList(vo));
		model.addAttribute("vo", vo);
		return "xdm/team/TeamXdmList";
	}
	
	@RequestMapping(value = "/team/TeamXdmForm")
	public String teamXdmForm(Model model) {
		model.addAttribute("league",teamService.league());
		model.addAttribute("division",teamService.division());
	return "xdm/team/TeamXdmForm"; 
	}
	
	@RequestMapping(value = "/team/TeamXdmInst")
	public String teamXdmInst(TeamDto teamDto) {
		teamService.insert(teamDto);
		
		return "redirect:TeamXdmList";
	}
}
