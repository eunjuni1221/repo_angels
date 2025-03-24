package com.angels.module.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CodeGroupController {
	
	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/codegroup/codeGroupXdmList")
	public String codegroupXdmList(Model model, CodeGroupVo vo) throws Exception{
		System.out.println(vo.getThisPage());
		vo.setParamsPaging(codeGroupService.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
		model.addAttribute("list", codeGroupService.selectList(vo));
		}
		
		model.addAttribute("vo", vo);
	return "xdm/codegroup/CodeGroupXdmList"; 
	}
	
	@RequestMapping(value = "/codegroup/CodeGroupXdmForm")
	public String codegroupXdmForm() {
		
	return "xdm/codegroup/CodeGroupXdmForm"; 
	}
	
	@RequestMapping(value = "/codegroup/CodeGroupXdmInst")
	public String codegroupXdmInst(CodeGroupDto codeGroupDto) {
		
		codeGroupService.insert(codeGroupDto);
	return "redirect:codeGroupXdmList"; 
	}
	
	
	
	

}
