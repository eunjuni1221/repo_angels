package com.angels.module.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angels.module.Base.BaseController;

@Controller
public class PlayerController extends BaseController {
	
	@Autowired
	PlayerService playerService;
	
	@RequestMapping(value="/player/PlayerXdmList")
	public String playerXdmList(Model model, PlayerVo vo) throws Exception {
		setSearch(vo);
		
		vo.setParamsPaging(playerService.selectOneCount(vo));
		
		if(vo.getTotalRows() > 0) {
		model.addAttribute("list", playerService.selectList(vo));
		}
		
		model.addAttribute("vo", vo);
		return "xdm/player/PlayerXdmList";
	}
	
	@RequestMapping(value = "/player/PlayerXdmForm")
	public String playerXdmForm(@ModelAttribute("vo") PlayerVo vo, Model model, PlayerDto dto) throws Exception {
		model.addAttribute("selectTeam",playerService.selectTeam());
		model.addAttribute("selectPosition",playerService.selectPosition());
		model.addAttribute("selectThrowHand",playerService.selectThrowHand());
		model.addAttribute("selectBatHand",playerService.selectBatHand());
		model.addAttribute("selectNationality",playerService.selectNationality());
		model.addAttribute("selectStatus",playerService.selectStatus());
		
		if (vo.getPySeq().equals("0") || vo.getPySeq().equals("")) {
//			insert mode
			model.addAttribute("item", dto);
		} else {
//			update mode
			model.addAttribute("item", playerService.selectOne(dto));
		}
		
	return "xdm/player/PlayerXdmForm"; 
	}
	
	@RequestMapping(value = "/player/PlayerXdmInst")
	public String playerXdmInst(PlayerDto playerDto) {
		playerService.insert(playerDto);	
		return "redirect:PlayerXdmList";
	}
	
	@RequestMapping(value = "/player/PlayerXdmUpdt")
	public String playerXdmUpdt(PlayerDto playerDto) {
		playerService.update(playerDto);		
		return "redirect:PlayerXdmList";
	}
	
	@RequestMapping(value = "/player/PlayerXdmUele")
	public String playerXdmUele(PlayerDto playerDto) {
		playerService.uelete(playerDto);		
		return "redirect:PlayerXdmList";
	}
	
	@RequestMapping(value = "/player/PlayerXdmDele")
	public String playerXdmDele(PlayerDto playerDto) {
		playerService.delete(playerDto);		
		return "redirect:PlayerXdmList";
	}
}
