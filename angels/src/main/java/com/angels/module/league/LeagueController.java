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
	    String apiUrl = "https://statsapi.mlb.com/api/v1/venues?sportId=1&hydrate=location,fieldInfo,timezone"; // MLB 구장 전체 API
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
	    JsonNode venues = root.get("venues");

	    for (JsonNode venue : venues) {
	        LeagueDto dto = new LeagueDto();
	        dto.setStSeq(venue.get("id").asText());
	        dto.setStName(venue.get("name").asText());

	        JsonNode location = venue.get("location");
	        if (location != null) {
	            dto.setStCity(location.has("city") ? location.get("city").asText() : null);
	            dto.setStState(location.has("state") ? location.get("state").asText() : null);
	            dto.setStStateAbbr(location.has("stateAbbrev") ? location.get("stateAbbrev").asText() : null);
	            dto.setStAddress(location.has("address1") ? location.get("address1").asText() : null);
	        }

	        JsonNode fieldInfo = venue.get("fieldInfo");
	        if (fieldInfo != null && fieldInfo.has("roofType")) {
	            dto.setStRoofType(fieldInfo.get("roofType").asText());
	        } else {
	            dto.setStRoofType(null);
	        }

	        dto.setStActive(venue.has("active") && venue.get("active").asBoolean() ? 1 : 0);
	        dto.setStDelNy(0);
	        dto.setStRegTime(LocalDateTime.now().toString());
	        dto.setStModTime(LocalDateTime.now().toString());

	        leagueService.insert(dto); // 실제 DB 저장
	    }

	    return "redirect:/league/LeagueXdmList";
	}
	
}

	
