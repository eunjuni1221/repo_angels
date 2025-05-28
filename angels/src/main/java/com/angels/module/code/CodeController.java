package com.angels.module.code;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angels.module.Base.BaseController;
import com.angels.module.team.TeamDto;
import com.angels.module.team.TeamVo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CodeController extends BaseController{
	
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value="/code/CodeXdmList")
	public String codeXdmList(Model model, CodeVo vo, CodeDto dto) throws Exception {
		setSearch(vo);
		
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
	
	@RequestMapping("/excelCodeDownload")
    public void excelDownload(CodeVo vo, HttpServletResponse httpServletResponse) throws Exception {
		
		setSearch(vo);
		vo.setParamsPaging(codeService.selectOneCount(vo));

		if (vo.getTotalRows() > 0) {
			List<CodeDto> list = codeService.selectList(vo);
			
//			Workbook workbook = new HSSFWorkbook();	// for xls
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("첫번째 시트");
	        CellStyle cellStyle = workbook.createCellStyle();        
	        Row row = null;
	        Cell cell = null;
	        int rowNum = 0;
			
//	        each column width setting
	        sheet.setColumnWidth(0, 2100);
	        sheet.setColumnWidth(1, 3100);

//	        Header
	        String[] tableHeader = {"이름", "영어이름", "등록일",""};

	        row = sheet.createRow(rowNum++);
			for(int i=0; i<tableHeader.length; i++) {
				cell = row.createCell(i);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
				cell.setCellValue(tableHeader[i]);
			}

//	        Body
	        for (int i=0; i<list.size(); i++) {
	            row = sheet.createRow(rowNum++);
	            
//	            String type: null 전달 되어도 ok
//	            int, date type: null 시 오류 발생 하므로 null check
//	            String type 이지만 정수형 데이터가 전체인 seq 의 경우 캐스팅
	            
	            cell = row.createCell(0);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
	            cell.setCellValue(list.get(i).getCdName());
	            
	            cell = row.createCell(1);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
	        	cell.setCellValue(list.get(i).getCdNameEng());
	        		        	
	            cell = row.createCell(3);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
	        	cell.setCellValue(list.get(i).getCdRegDate());      
	            
	        }

	        httpServletResponse.setContentType("ms-vnd/excel");
//	        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=example.xls");	// for xls
	        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

	        workbook.write(httpServletResponse.getOutputStream());
	        workbook.close();
		}
    }
}
