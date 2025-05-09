package com.angels.module.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameController {
	
	@Autowired
	GameService gameService;
		
	@RequestMapping(value="/game/GameXdmList")
	public String gameXdmList(Model model, GameVo vo) throws Exception{
		setSearch(vo);
		
		model.addAttribute("list", gameService.selectList(vo));
		
		return "xdm/game/GameXdmList";
	}

}
