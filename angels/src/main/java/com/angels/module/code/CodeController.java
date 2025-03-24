package com.angels.module.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeController {
	
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value="/code/CodeXdmList")
	public String codeXdmList(Model model, CodeVo vo) throws Exception {
		System.out.println(vo.getThisPage());
		vo.setParamsPaging(codeService.selectOneCount(vo));
		
		if(vo.getTotalRows() > 0) {
		model.addAttribute("list", codeService.selectList(vo));
		}
		
		model.addAttribute("vo", vo);
		return "xdm/code/CodeXdmList";
	}
	
	@RequestMapping(value="/code/CodeXdmForm")
	public String codeXdmForm(Model model, CodeVo vo) {
		model.addAttribute("listCodeGroup",codeService.listCodeGroup(vo));
		return "xdm/code/CodeXdmForm";
	}
	
	@RequestMapping(value="/code/CodeXdmInst")
	public String codeXdmInst(CodeDto codeDto) {
		codeService.insert(codeDto);
		return "redirect:CodeXdmList";
	}
}
