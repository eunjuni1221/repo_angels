package com.angels.module.team;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angels.module.Base.BaseController;
import com.angels.module.code.CodeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	
}
