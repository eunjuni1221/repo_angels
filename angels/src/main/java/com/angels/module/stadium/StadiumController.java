package com.angels.module.stadium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angels.module.Base.BaseController;

@Controller
public class StadiumController extends BaseController{
	
	@Autowired
	StadiumService stadiumService;
	
	@RequestMapping(value="/stadium/StadiumXdmList")
	public String stadiumXdmList(Model model, StadiumVo vo) throws Exception {
		setSearch(vo);
		
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
	public String stadiumXdmForm(@ModelAttribute("vo") StadiumVo vo, Model model, StadiumDto dto) throws Exception {
		model.addAttribute("selectTeam",stadiumService.selectTeam());
		
		if (vo.getStSeq().equals("0") || vo.getStSeq().equals("")) {
//			insert mode
			model.addAttribute("item", dto);
		} else {
//			update mode
			model.addAttribute("item", stadiumService.selectOne(dto));
		}
		
		
	return "xdm/stadium/StadiumXdmForm"; 
	}
	
	@RequestMapping(value = "/stadium/StadiumXdmInst")
	public String stadiumXdmInst(StadiumDto stadiumDto) {
		stadiumService.insert(stadiumDto);
		
		return "redirect:StadiumXdmList";
	}
	
	@RequestMapping(value = "/stadium/StadiumXdmUpdt")
	public String stadiumXdmUpdt(StadiumDto stadiumDto) {
		stadiumService.update(stadiumDto);
		
		return "redirect:StadiumXdmList";
	}
	
	@RequestMapping(value = "/stadium/StadiumXdmUele")
	public String stadiumXdmUele(StadiumDto stadiumDto) {
		stadiumService.uelete(stadiumDto);
		
		return "redirect:StadiumXdmList";
	}
	
	@RequestMapping(value = "/stadium/StadiumXdmDele")
	public String stadiumXdmDele(StadiumDto stadiumDto) {
		stadiumService.delete(stadiumDto);
		
		return "redirect:StadiumXdmList";
	}

}
