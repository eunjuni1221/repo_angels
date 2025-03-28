package com.angels.module.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angels.module.code.CodeService;
import com.angels.module.code.CodeVo;

import jakarta.servlet.http.HttpSession;


@Controller
public class CodeGroupController {
	
	@Autowired
	CodeGroupService codeGroupService;
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/codegroup/CodeGroupXdmList")
	public String codegroupXdmList(Model model, CodeGroupVo vo, CodeGroupDto dto,
			HttpSession httpSession) throws Exception{
		
		if(httpSession.getAttribute("sessXdmSeq") == null) {
			return "redirect:/login/Login"; 
		}
		
		System.out.println(vo.getThisPage());
		vo.setParamsPaging(codeGroupService.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
		model.addAttribute("list", codeGroupService.selectList(vo));
		}
		
		model.addAttribute("vo", vo);
	return "xdm/codegroup/CodeGroupXdmList"; 
	}
	
	@RequestMapping(value = "/codegroup/CodeGroupXdmForm")
	public String codegroupXdmForm(@ModelAttribute("vo") CodeGroupVo vo, Model model, CodeGroupDto codeGroupDto) throws Exception {
		
		if (vo.getCgSeq().equals("0") || vo.getCgSeq().equals("")) {
//			insert mode
		} else {
//			update mode
			model.addAttribute("item", codeGroupService.selectOne(codeGroupDto));
		}
		
	return "xdm/codegroup/CodeGroupXdmForm"; 
	}
	
	@RequestMapping(value = "/codegroup/CodeGroupXdmInst")
	public String codegroupXdmInst(CodeGroupDto codeGroupDto) {
		
		codeGroupService.insert(codeGroupDto);
	return "redirect:CodeGroupXdmList"; 
	}
	
	@RequestMapping(value = "/codegroup/CodeGroupXdmUpdt")
	public String codegroupXdmUpdt(CodeGroupDto codeGroupDto) {
		System.out.println(codeGroupDto.getCgSeq());
		codeGroupService.update(codeGroupDto);
		return "redirect:CodeGroupXdmList"; 
	}
	
	@RequestMapping(value = "/codegroup/CodeGroupXdmUele")
	public String codegroupXdmUele(CodeGroupDto codeGroupDto) {

		codeGroupService.uelete(codeGroupDto);
		return "redirect:CodeGroupXdmList"; 
	}
	
	@RequestMapping(value = "/codegroup/CodeGroupXdmDele")
	public String codegroupXdmDele(CodeGroupDto codeGroupDto) {

		codeGroupService.delete(codeGroupDto);
		return "redirect:CodeGroupXdmList"; 
	}
	
	

}
