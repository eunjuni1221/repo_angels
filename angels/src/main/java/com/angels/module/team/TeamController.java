package com.angels.module.team;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
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
import com.angels.module.code.CodeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

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
	
//	@RequestMapping(value = "/team/TeamXdmInst")
//	public String teamXdmInst(TeamDto teamDto) {
//		teamService.insert(teamDto);
//		
//		return "redirect:TeamXdmList";
//	}
	
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
	public String teamHofMainList(Model model, TeamDto dto) {
		
		model.addAttribute("list", teamService.selectHofList());
		
		return "hof/team/baseball_team-mainmain"; 
	}
	
	@RequestMapping(value = "/team/TeamHofMain")
	public String teamHofMain(Model model, TeamDto dto) {
		model.addAttribute("item", teamService.selectOne(dto));
		return "hof/team/baseball_team-main"; 
	}
	
	@RequestMapping(value = "/team/TeamXdmInst")
	public String teamXdmInst() throws Exception {
	    // MLB API 호출
	    String apiUrl = "https://statsapi.mlb.com/api/v1/teams?sportId=1";
	    URL url = new URL(apiUrl);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");

	    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    StringBuilder response = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        response.append(line);
	    }
	    reader.close();
	    conn.disconnect();

	    // JSON 파싱
	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode root = objectMapper.readTree(response.toString());
	    JsonNode teams = root.get("teams");

	    for (JsonNode team : teams) {
	        String logoUrl = "https://www.mlbstatic.com/team-logos/team-baseball/" + team.get("fileCode").asText() + ".svg";
	        
	        TeamDto dto = new TeamDto();
	        dto.setTmSeq(team.get("id").asText());
	        dto.setTmName(team.get("name").asText());
	        dto.setTmAbbreviation(team.get("abbreviation").asText());
	        dto.setTmLocation(team.get("locationName").asText());
	        dto.setTmClubName(team.get("teamName").asText());
	        dto.setTmStPlay(team.get("firstYearOfPlay").asText());
	        dto.setTmLogoUrl(logoUrl);
	        dto.setTmActive(1);
	        dto.setTmDelNy(0);
	        dto.setTmRegTime(LocalDateTime.now().toString());

	        // 추가된 부분: league, division, venue FK 처리
	        if (team.has("league") && team.get("league").has("id")) {
	            dto.setLeague_lgSeq(team.get("league").get("id").asText());
	        }

	        if (team.has("division") && team.get("division").has("id")) {
	            dto.setDivision_dvSeq(team.get("division").get("id").asText());
	        }

	        if (team.has("venue") && team.get("venue").has("id")) {
	            dto.setVenue_stSeq(team.get("venue").get("id").asText());
	        }

	        // DB 저장
	        teamService.insert(dto);
	    }

	    return "redirect:TeamXdmList";
	}
	
	@RequestMapping("/excelDownload")
    public void excelDownload(TeamVo vo, HttpServletResponse httpServletResponse) throws Exception {
		
		setSearch(vo);
		vo.setParamsPaging(teamService.selectOneCount(vo));

		if (vo.getTotalRows() > 0) {
			List<TeamDto> list = teamService.selectList(vo);
			
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
	        String[] tableHeader = {"팀명", "약어", "연고지", "리그", "수정일",""};

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
	            cell.setCellValue(Integer.parseInt(list.get(i).getIfmName()));
	            
	            cell = row.createCell(1);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
	        	cell.setCellValue(list.get(i).getIfcgName());
	        	
	            cell = row.createCell(2);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
	        	cell.setCellValue(Integer.parseInt(list.get(i).getIfcdSeq()));
	        	
	            cell = row.createCell(3);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
	        	cell.setCellValue(list.get(i).getIfcdSeqAnother());
	            
	            cell = row.createCell(4);
	            cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            cell.setCellStyle(cellStyle);
	            cell.setCellValue(list.get(i).getIfcdName());
	            
	            
	        }

	        httpServletResponse.setContentType("ms-vnd/excel");
//	        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=example.xls");	// for xls
	        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

	        workbook.write(httpServletResponse.getOutputStream());
	        workbook.close();
		}
    }
	
}
