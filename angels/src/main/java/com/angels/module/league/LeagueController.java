package com.angels.module.league;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angels.module.Base.BaseController;
import com.angels.module.stadium.StadiumDto;
import com.angels.module.stadium.StadiumVo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LeagueController extends BaseController{
	
	@Autowired
	LeagueService leagueService;
	
	@RequestMapping(value="/league/LeagueXdmList")
	public String leagueXdmList(Model model, LeagueVo vo) throws Exception {
		setSearch(vo);
		
		System.out.println(vo.getThisPage());
		vo.setParamsPaging(leagueService.selectOneCount(vo));
//		model.addAttribute("selectTeam",stadiumService.selectTeam());

		
		if(vo.getTotalRows() > 0) {
		model.addAttribute("list", leagueService.selectList(vo));
		}
		model.addAttribute("vo", vo);
		return "xdm/league/LeagueXdmList";
	}
	
	@RequestMapping(value = "/league/LeagueXdmInst")
	public String leagueXdmInst() throws Exception {
	    String apiUrl = "https://statsapi.mlb.com/api/v1/league";
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

	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode root = objectMapper.readTree(response.toString());
	    JsonNode leagues = root.get("leagues");

	    for (JsonNode league : leagues) {
	        LeagueDto dto = new LeagueDto();

	        dto.setLgSeq(league.get("id").asText());
	        dto.setLgName(league.get("name").asText());
	        dto.setLgAbbreviation(league.has("abbreviation") ? league.get("abbreviation").asText() : null);
	        dto.setLgSeasonState(league.has("seasonState") ? league.get("seasonState").asText() : null);
	        dto.setLgActive(league.has("active") && league.get("active").asBoolean() ? 1 : 0);
	        dto.setLgDelNy(0);
	        dto.setLgRegTime(LocalDateTime.now().toString());
	        dto.setLgModTime(LocalDateTime.now().toString());

	        leagueService.insert(dto);
	    }

	    return "redirect:/league/LeagueXdmList";
	}

	
}

	
