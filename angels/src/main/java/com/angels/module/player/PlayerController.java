package com.angels.module.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angels.module.team.TeamDto;
import com.angels.module.team.TeamVo;

@Controller
public class PlayerController {
	
	@Autowired
	PlayerService playerService;
	
	@RequestMapping(value="/player/PlayerXdmList")
	public String playerXdmList(Model model, PlayerVo vo) throws Exception {
		vo.setParamsPaging(playerService.selectOneCount(vo));
		model.addAttribute("selectTeam",playerService.selectTeam());
		
		if(vo.getTotalRows() > 0) {
			System.out.println(vo.getShUseNy() + "@@@@@@@@@@@@@@@@@@@@@@");
		model.addAttribute("list", playerService.selectList(vo));
		}
		
		model.addAttribute("vo", vo);
		return "xdm/player/PlayerXdmList";
	}
	
	@RequestMapping(value = "/player/PlayerXdmForm")
	public String playerXdmForm(Model model) {
		model.addAttribute("selectTeam",playerService.selectTeam());
		model.addAttribute("selectPosition",playerService.selectPosition());
		model.addAttribute("selectThrowHand",playerService.selectThrowHand());
		model.addAttribute("selectBatHand",playerService.selectBatHand());
		model.addAttribute("selectNationality",playerService.selectNationality());
		model.addAttribute("selectStatus",playerService.selectStatus());
	return "xdm/player/PlayerXdmForm"; 
	}
	
	@RequestMapping(value = "/player/PlayerXdmInst")
	public String playerXdmInst(PlayerDto playerDto) {
		playerService.insert(playerDto);
		
		return "redirect:PlayerXdmList";
	}
	
}
