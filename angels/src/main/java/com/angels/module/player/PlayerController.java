package com.angels.module.player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angels.module.Base.BaseController;
import com.angels.module.user.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PlayerController extends BaseController {
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/player/PlayerXdmList")
	public String playerXdmList(Model model, PlayerVo vo) throws Exception {
		setSearch(vo);
		
		vo.setParamsPaging(playerService.selectOneCount(vo));
//		model.addAttribute("selectTeam",playerService.selectTeam());
		
		if(vo.getTotalRows() > 0) {
		model.addAttribute("list", playerService.selectList(vo));
		}
		
		model.addAttribute("vo", vo);
		return "xdm/player/PlayerXdmList";
	}
	
//	@RequestMapping(value = "/player/PlayerXdmForm")
//	public String playerXdmForm(@ModelAttribute("vo") PlayerVo vo, Model model, PlayerDto dto) throws Exception {
//		model.addAttribute("selectTeam",playerService.selectTeam());
//		model.addAttribute("selectPosition",playerService.selectPosition());
//		model.addAttribute("selectThrowHand",playerService.selectThrowHand());
//		model.addAttribute("selectBatHand",playerService.selectBatHand());
//		model.addAttribute("selectNationality",playerService.selectNationality());
//		model.addAttribute("selectStatus",playerService.selectStatus());
//		
//		if (vo.getPySeq().equals("0") || vo.getPySeq().equals("")) {
//			insert mode
//			model.addAttribute("item", dto);
//		} else {
//			update mode
//			model.addAttribute("item", playerService.selectOne(dto));
//		}
//		
//	return "xdm/player/PlayerXdmForm"; 
//	}
	
//	@RequestMapping(value = "/player/PlayerXdmInst")
//	public String playerXdmInst(PlayerDto playerDto) throws Exception {
//		playerService.insert(playerDto);		
//		return "redirect:PlayerXdmList";
//	}

	@RequestMapping(value = "/player/PlayerXdmInst")
	public String playerXdmInst(PlayerDto playerDto) throws Exception {
		// 1. MLB 팀 리스트 API로 teamId 가져오기
	    String teamApiUrl = "https://statsapi.mlb.com/api/v1/teams?sportId=1";
	    URL teamUrl = new URL(teamApiUrl);
	    HttpURLConnection teamConn = (HttpURLConnection) teamUrl.openConnection();
	    teamConn.setRequestMethod("GET");

	    BufferedReader teamReader = new BufferedReader(new InputStreamReader(teamConn.getInputStream()));
	    StringBuilder teamResponse = new StringBuilder();
	    String teamLine;
	    while ((teamLine = teamReader.readLine()) != null) {
	        teamResponse.append(teamLine);
	    }
	    teamReader.close();
	    teamConn.disconnect();

	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode teamRoot = mapper.readTree(teamResponse.toString());
	    JsonNode teams = teamRoot.get("teams");

	    for (JsonNode team : teams) {
	        String teamId = team.get("id").asText();

	        // 2. 팀별 40인 로스터 API 호출
	        String rosterApiUrl = "https://statsapi.mlb.com/api/v1/teams/" + teamId + "/roster/40Man?hydrate=person";
	        URL rosterUrl = new URL(rosterApiUrl);
	        HttpURLConnection rosterConn = (HttpURLConnection) rosterUrl.openConnection();
	        rosterConn.setRequestMethod("GET");

	        BufferedReader rosterReader = new BufferedReader(new InputStreamReader(rosterConn.getInputStream()));
	        StringBuilder rosterResponse = new StringBuilder();
	        String rosterLine;
	        while ((rosterLine = rosterReader.readLine()) != null) {
	            rosterResponse.append(rosterLine);
	        }
	        rosterReader.close();
	        rosterConn.disconnect();

	        JsonNode rosterRoot = mapper.readTree(rosterResponse.toString());
	        JsonNode roster = rosterRoot.get("roster");

	        for (JsonNode item : roster) {
	            JsonNode person = item.get("person");
	            PlayerDto dto = new PlayerDto();

	            dto.setPySeq(person.get("id").asText());
	            dto.setPyFullName(person.get("fullName").asText());
	            dto.setPyFirstname(person.has("firstName") ? person.get("firstName").asText() : null);
	            dto.setPyLastName(person.has("lastName") ? person.get("lastName").asText() : null);
	            dto.setPyNameSlug(person.has("nameSlug") ? person.get("nameSlug").asText() : null);
	            dto.setPyBirthDay(person.has("birthDate") ? person.get("birthDate").asText() : null);
	            dto.setPyBirthCity(person.has("birthCity") ? person.get("birthCity").asText() : null);
	            dto.setPyBirthCountry(person.has("birthCountry") ? person.get("birthCountry").asText() : null);
	            dto.setPyHeight(person.has("height") ? person.get("height").asText() : null);
	            dto.setPyWeight(person.has("weight") ? person.get("weight").asInt() : null);
	            dto.setPyBat(person.has("batSide") ? person.get("batSide").get("code").asText() : null);
	            dto.setPyPitch(person.has("pitchHand") ? person.get("pitchHand").get("code").asText() : null);
	            dto.setPyDebut(person.has("mlbDebutDate") ? person.get("mlbDebutDate").asText() : null);
	            dto.setPyActive(1);
	            dto.setPyDelNy(0);
	            dto.setPyNickName(person.has("nickName") ? person.get("nickName").asText() : null);
	            dto.setPyBoxScoreName(person.has("boxscoreName") ? person.get("boxscoreName").asText() : null);
	            dto.setPyJersey(person.has("primaryNumber") ? Integer.parseInt(person.get("primaryNumber").asText()) : null);
	            dto.setPyPositionCode(person.has("primaryPosition") ? person.get("primaryPosition").get("code").asText() : null);
	            dto.setPyPositionName(person.has("primaryPosition") ? person.get("primaryPosition").get("name").asText() : null);
	            dto.setPyPositionAbbr(person.has("primaryPosition") ? person.get("primaryPosition").get("abbreviation").asText() : null);

	            if (person.has("birthDate")) {
	                LocalDate birthDate = LocalDate.parse(person.get("birthDate").asText());
	                int age = Period.between(birthDate, LocalDate.now()).getYears();
	                dto.setPyAge(age);
	            }

	            String photoUrl = "https://midfield.mlbstatic.com/v1/people/" + person.get("id").asText() + "/headshot/67/current";
	            dto.setPyPhoto(photoUrl);

	            dto.setTeam_tmSeq(teamId);
	            dto.setPyRegTime(LocalDateTime.now().toString());
	            dto.setPyModTime(LocalDateTime.now().toString());

	            playerService.insert(dto);
	        }
	    }

	    return "redirect:/player/playerXdmList";
	}
	
	@RequestMapping(value = "/player/PlayerXdmUpdt")
	public String playerXdmUpdt(PlayerDto playerDto) {
		playerService.update(playerDto);		
		return "redirect:PlayerXdmList";
	}
	
	@RequestMapping(value = "/player/PlayerXdmUele")
	public String playerXdmUele(PlayerDto playerDto) {
		playerService.uelete(playerDto);		
		return "redirect:PlayerXdmList";
	}
	
	@RequestMapping(value = "/player/PlayerXdmDele")
	public String playerXdmDele(PlayerDto playerDto) {
		playerService.delete(playerDto);		
		return "redirect:PlayerXdmList";
	}
	
	@RequestMapping(value = "/player/PlayerHofSearch")
	public String playerHofSearch(@ModelAttribute("vo") PlayerVo vo, Model model, PlayerDto dto) {
		setSearch(vo);
	
		model.addAttribute("selectTeam",playerService.selectTeam());
		model.addAttribute("selectPosition",playerService.selectPosition());
		
		 boolean isSearched = vo.getShTeamOption() != null && !vo.getShTeamOption().isEmpty()
                 || vo.getShPosition() != null && !vo.getShPosition().isEmpty()
                 || vo.getShValue() != null && !vo.getShValue().isEmpty();

			if (isSearched) {
			   vo.setParamsPaging(playerService.selectOneCount(vo));
			   model.addAttribute("list", playerService.selectHofList(vo));
			   model.addAttribute("searched", true);
			} else {
			   model.addAttribute("list", Collections.emptyList());
			   model.addAttribute("searched", false);
			}

		return "hof/player/baseball_player-search";
	}
}
