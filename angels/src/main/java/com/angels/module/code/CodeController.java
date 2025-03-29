package com.angels.module.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeController {
	
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value="/code/CodeXdmList")
	public String codeXdmList(Model model, CodeVo vo, CodeDto dto) throws Exception {
		System.out.println(vo.getThisPage());
		vo.setParamsPaging(codeService.selectOneCount(vo));
		
		if(vo.getTotalRows() > 0) {
		model.addAttribute("list", codeService.selectList(vo));
		}
		
		model.addAttribute("vo", vo);
		return "xdm/code/CodeXdmList";
	}
	
	@RequestMapping(value="/code/CodeXdmForm")
	public String codeXdmForm(@ModelAttribute("vo") CodeVo vo, Model model, CodeDto dto) throws Exception{
		model.addAttribute("listCodeGroup",codeService.listCodeGroup(vo));
		
		if (vo.getCdSeq().equals("0") || vo.getCdSeq().equals("")) {
//			insert mode
			model.addAttribute("item", dto);
		} else {
//			update mode
			model.addAttribute("item", codeService.selectOne(dto));
		}
		
		return "xdm/code/CodeXdmForm";
	}
	
	@RequestMapping(value="/code/CodeXdmInst")
	public String codeXdmInst(CodeDto codeDto) {
		codeService.insert(codeDto);
		return "redirect:CodeXdmList";
	}
	
	@RequestMapping(value="/code/CodeXdmUpdt")
	public String codeXdmUpdt(CodeDto codeDto) {
		System.out.println(codeDto.getCgSeq());
		codeService.update(codeDto);
		return "redirect:CodeXdmList";
	}
	
	@RequestMapping(value="/code/CodeXdmUele")
	public String codeXdmUele(CodeDto codeDto) {
		codeService.uelete(codeDto);
		return "redirect:CodeXdmList";
	}
	
	@RequestMapping(value="/code/CodeXdmDele")
	public String codeXdmDele(CodeDto codeDto) {
		codeService.delete(codeDto);
		return "redirect:CodeXdmList";
	}
}
