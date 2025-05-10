package com.angels.module.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class GameController {
	
	@Autowired
	GameService gameService;
		
	@RequestMapping(value="/game/GameXdmList")
	public String gameXdmList(Model model, GameVo vo) throws Exception{
		
		vo.setParamsPaging(gameService.selectOneCount(vo));
			
		if(vo.getTotalRows() > 0) {
		model.addAttribute("list", gameService.selectList(vo));
		}
		
		model.addAttribute("vo", vo);
		return "xdm/game/GameXdmList";
	}
	
    @RequestMapping("/game/GameXdmInst")
    public String gameXdmInst() throws Exception {
        String apiUrl = "https://statsapi.mlb.com/api/v1/schedule?sportId=1&season=2025";
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

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.toString());
        JsonNode dates = root.get("dates");

        for (JsonNode dateNode : dates) {
            JsonNode games = dateNode.get("games");
            for (JsonNode game : games) {
                GameDto dto = new GameDto();

                dto.setGmSeq(game.get("gamePk").asText());
                dto.setGmGuid(game.has("gameGuid") ? game.get("gameGuid").asText() : null);
                dto.setGmSeason(game.get("season").asText());
                dto.setGmDate(game.has("gameDate") ? game.get("gameDate").asText().replace("Z", "") : null);
                dto.setGmOfDate(game.has("officialDate") ? game.get("officialDate").asText() : null);
                dto.setGmState(game.has("status") && game.get("status").has("abstractGameState") ? game.get("status").get("abstractGameState").asText() : null);
                dto.setGmDeState(game.has("status") && game.get("status").has("detailedState") ? game.get("status").get("detailedState").asText() : null);
                dto.setGmStatusCode(game.has("status") && game.get("status").has("statusCode") ? game.get("status").get("statusCode").asText() : null);
                dto.setGmDayNight(game.has("dayNight") ? game.get("dayNight").asText() : null);
                dto.setGmScInnings(game.has("scheduledInnings") ? game.get("scheduledInnings").asInt() : null);
                dto.setGmIsTie(game.has("isTie") ? (game.get("isTie").asBoolean() ? 1 : 0) : 0);
                dto.setGmSeriesDesc(game.has("seriesDescription") ? game.get("seriesDescription").asText() : null);
                dto.setGmDoubleheader(game.has("doubleHeader") ? game.get("doubleHeader").asText() : null);
                dto.setGmType(game.has("gameType") ? game.get("gameType").asText() : null);

                dto.setVenue_stSeq(game.has("venue") && game.get("venue").has("id") ? game.get("venue").get("id").asText() : null);
                dto.setHomeTeam_tmSeq(game.has("teams") && game.get("teams").has("home") && game.get("teams").get("home").get("team").has("id")
                        ? game.get("teams").get("home").get("team").get("id").asText() : null);
                dto.setAwayTeam_tmSeq(game.has("teams") && game.get("teams").has("away") && game.get("teams").get("away").get("team").has("id")
                        ? game.get("teams").get("away").get("team").get("id").asText() : null);

                dto.setGmRegTime(LocalDateTime.now().toString());
                dto.setGmModTime(LocalDateTime.now().toString());

                gameService.insert(dto);
            }
        }

        return "redirect:xdm/game/GameXdmList";
    }
}


