package com.angels.module.division;

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
import com.angels.module.league.LeagueService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DivisionController extends BaseController{
	
	@Autowired
	DivisionService divisionService;
	
	@Autowired
	LeagueService leagueService;
	
	@RequestMapping(value="/division/DivisionXdmList")
	public String leagueXdmList(Model model, DivisionVo vo) throws Exception {
		setSearch(vo);
		
		System.out.println(vo.getThisPage());
		vo.setParamsPaging(divisionService.selectOneCount(vo));
//		model.addAttribute("selectTeam",stadiumService.selectTeam());

		
		if(vo.getTotalRows() > 0) {
		model.addAttribute("list", divisionService.selectList(vo));
		}
		model.addAttribute("vo", vo);
		return "xdm/division/DivisionXdmList";
	}
	
	@RequestMapping(value = "/division/DivisionXdmInst")
	public String divisionXdmInst() throws Exception {
	    String apiUrl = "https://statsapi.mlb.com/api/v1/divisions";
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
	    JsonNode divisions = root.get("divisions");

	    for (JsonNode division : divisions) {
	        DivisionDto dto = new DivisionDto();

	        dto.setDvSeq(division.get("id").asText());
	        dto.setDvName(division.get("name").asText());
	        dto.setDvNameAb(division.has("abbreviation") ? division.get("abbreviation").asText() : null);
	        dto.setDvActive(division.has("active") && division.get("active").asBoolean() ? 1 : 0);
	        dto.setDvDelNy(0);
	        dto.setDvRegTime(LocalDateTime.now().toString());
	        dto.setDvModTime(LocalDateTime.now().toString());

	        // 리그 연결
	        if (division.has("league") && division.get("league").has("id")) {
	            String leagueId = division.get("league").get("id").asText();

	            // 리그가 실제 존재하는지 확인 (없으면 건너뜀)
	            if (!leagueService.exists(leagueId)) {
	                System.out.println("🔁 해당 리그 없음, 건너뜀 → leagueId: " + leagueId);
	                continue;
	            }

	            dto.setLeague_lgSeq(leagueId);
	        } else {
	            // 리그 정보 자체가 없을 경우도 건너뜀
	            continue;
	        }

	        divisionService.insert(dto);
	    }

	    return "redirect:/division/DivisionXdmList";
	}



}
