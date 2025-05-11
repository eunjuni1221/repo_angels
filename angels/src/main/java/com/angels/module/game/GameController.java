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

import com.angels.module.Base.BaseController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class GameController extends BaseController{
	
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

        int totalCount = 0;
        int successCount = 0;

        for (JsonNode dateNode : dates) {
            JsonNode games = dateNode.get("games");
            for (JsonNode game : games) {
                totalCount++;

                GameDto dto = new GameDto();

                dto.setGmSeq(game.get("gamePk").asText());
                dto.setGmGuid(game.has("gameGuid") ? game.get("gameGuid").asText() : null);
                dto.setGmSeason(game.get("season").asText());
                dto.setGmDate(game.has("gameDate") ? game.get("gameDate").asText().replace("Z", "") : null);
                dto.setGmOfDate(game.has("officialDate") ? game.get("officialDate").asText() : null);
                dto.setGmState(game.path("status").path("abstractGameState").asText(null));
                dto.setGmDeState(game.path("status").path("detailedState").asText(null));
                dto.setGmStatusCode(game.path("status").path("statusCode").asText(null));
                dto.setGmDayNight(game.path("dayNight").asText(null));
                dto.setGmScInnings(game.has("scheduledInnings") ? game.get("scheduledInnings").asInt() : null);
                dto.setGmIsTie(game.has("isTie") ? (game.get("isTie").asBoolean() ? 1 : 0) : 0);
                dto.setGmSeriesDesc(game.path("seriesDescription").asText(null));
                dto.setGmDoubleheader(game.has("doubleHeader") ? game.get("doubleHeader").asText() : null);
                dto.setGmType(game.path("gameType").asText(null));

                dto.setVenue_stSeq(game.path("venue").path("id").asText(null));
                dto.setHomeTeam_tmSeq(game.path("teams").path("home").path("team").path("id").asText(null));
                dto.setAwayTeam_tmSeq(game.path("teams").path("away").path("team").path("id").asText(null));

                dto.setGmRegTime(LocalDateTime.now().toString());
                dto.setGmModTime(LocalDateTime.now().toString());

                try {
                    gameService.insert(dto);
                    successCount++;
                    System.out.println("✅ INSERT 성공: gamePk = " + dto.getGmSeq());
                } catch (Exception e) {
                    System.out.println("❌ INSERT 실패: gamePk = " + dto.getGmSeq() + ", error = " + e.getMessage());
                }
            }
        }

        System.out.println("🏁 MLB 경기 수집 완료 | 총: " + totalCount + "개, 성공: " + successCount + "개");

        return "redirect:/game/GameXdmList";
    }

}


