package com.angels.module.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angels.module.Base.BaseController;
import com.angels.module.code.CodeService;

@Controller
public class TeamController extends BaseController{
	
	@Autowired
	TeamService teamService;
	CodeService codeService;
	
	@RequestMapping(value="/team/TeamXdmList")
	public String teamXdmList(Model model, TeamVo vo) throws Exception {
		
		setSearch(vo);
		
		System.out.println(vo.getThisPage());
		vo.setParamsPaging(teamService.selectOneCount(vo));
		
		if(vo.getTotalRows() > 0) {
		model.addAttribute("list", teamService.selectList(vo));
		}
		
		model.addAttribute("vo", vo);
		return "xdm/team/TeamXdmList";
	}
	
	@RequestMapping(value = "/team/TeamXdmForm")
	public String teamXdmForm(@ModelAttribute("vo") TeamVo vo, Model model, TeamDto dto) {
		model.addAttribute("league",teamService.league());
		model.addAttribute("division",teamService.division());
		
		if (vo.getTmSeq().equals("0") || vo.getTmSeq().equals("")) {
//			insert mode
			model.addAttribute("item", dto);
		} else {
//			update mode
			model.addAttribute("item", teamService.selectOne(dto));
		}
		
	return "xdm/team/TeamXdmForm"; 
	}
	
	@RequestMapping(value = "/team/TeamXdmInst")
	public String teamXdmInst(TeamDto teamDto) {
		teamService.insert(teamDto);
		
		return "redirect:TeamXdmList";
	}
	@RequestMapping(value = "/team/TeamXdmUpdt")
	public String teamXdmUpdt(TeamDto teamDto) {
		teamService.update(teamDto);
		
		return "redirect:TeamXdmList";
	}
	@RequestMapping(value = "/team/TeamXdmUele")
	public String teamXdmUele(TeamDto teamDto) {
		teamService.uelete(teamDto);
		
		return "redirect:TeamXdmList";
	}
	@RequestMapping(value = "/team/TeamXdmDele")
	public String teamXdmDele(TeamDto teamDto) {
		teamService.delete(teamDto);
		
		return "redirect:TeamXdmList";
	}
	@RequestMapping(value = "/team/TeamHofMainList")
	public String teamHofMainList() {

	return "hof/team/baseball_team-mainmain"; 
	}
}
