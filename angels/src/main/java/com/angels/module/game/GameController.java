package com.angels.module.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

	            // ✅ 정규시즌만 필터링
	            String gameType = game.path("gameType").asText("");
	            if (!"R".equals(gameType)) {
	                continue;
	            }

	            // ✅ 경기 시간 KST로 변환
	            String gmDateUtc = game.path("gameDate").asText(null); // UTC 시간
	            String gmDateKst = null;
	            if (gmDateUtc != null) {
	                Instant instant = Instant.parse(gmDateUtc);
	                LocalDateTime kstTime = instant.atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime();
	                gmDateKst = kstTime.toString(); // yyyy-MM-ddTHH:mm:ss
	            }

	            GameDto dto = new GameDto();

	            dto.setGmSeq(game.get("gamePk").asText());
	            dto.setGmGuid(game.has("gameGuid") ? game.get("gameGuid").asText() : null);
	            dto.setGmSeason(game.get("season").asText());
	            dto.setGmDate(gmDateKst); // ✅ 한국 시간으로 세팅
	            dto.setGmOfDate(game.has("officialDate") ? game.get("officialDate").asText() : null);
	            dto.setGmState(game.path("status").path("abstractGameState").asText(null));
	            dto.setGmDeState(game.path("status").path("detailedState").asText(null));
	            dto.setGmStatusCode(game.path("status").path("statusCode").asText(null));
	            dto.setGmDayNight(game.path("dayNight").asText(null));
	            dto.setGmScInnings(game.has("scheduledInnings") ? game.get("scheduledInnings").asInt() : null);
	            dto.setGmIsTie(game.has("isTie") ? (game.get("isTie").asBoolean() ? 1 : 0) : 0);
	            dto.setGmSeriesDesc(game.path("seriesDescription").asText(null));
	            dto.setGmDoubleheader(game.has("doubleHeader") ? game.get("doubleHeader").asText() : null);
	            dto.setGmType(gameType);

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

	    System.out.println("🏁 MLB 정규시즌 경기 수집 완료 | 총: " + totalCount + "개 중 성공: " + successCount + "개");

	    return "redirect:/game/GameXdmList";
	}

	
	
	@RequestMapping("/game/GameHofMinList")
	public String gameHofMinList() {
		return "hof/game/baseball_miniSche";
	}
	
	@RequestMapping("/game/GameHofMinListProc")
	public String gameHofMinListProc(Model model, GameDto dto) {
		model.addAttribute("list", gameService.selectHofList(dto));
		return "hof/game/baseball_miniScheTwo";
	}
	
	@RequestMapping("/game/GameLineScoreInst")
	public String gameLineScoreInst(GameDto dto) throws Exception {
	    System.out.println("=== [CALL] GameLineScoreInst ===");

	    List<GameDto> gameList = gameService.listInstForLineScore(dto);
	    System.out.println("조회된 경기 수: " + gameList.size());

	    int totalCount = 0;
	    int successCount = 0;

	    for (GameDto game : gameList) {
	        totalCount++;
	        String gamePk = game.getGmSeq();
	        String apiUrl = "https://statsapi.mlb.com/api/v1/game/" + gamePk + "/linescore";

	        try {
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
	            JsonNode innings = root.path("innings");

	            if (innings.isMissingNode() || !innings.isArray()) {
	                System.err.println("innings 데이터 없음 → 경기 건너뜀: gamePk=" + gamePk);
	                continue;
	            }

	            // === 1. gamelinescore INSERT (총계 정보) ===
	            GameDto lineScoreDto = new GameDto();
	            lineScoreDto.setGame_gmSeq(gamePk);
	            JsonNode teams = root.path("teams");
	            JsonNode homeTeam = teams.path("home");
	            JsonNode awayTeam = teams.path("away");

	            lineScoreDto.setGlHomeRuns(homeTeam.path("runs").asInt());
	            lineScoreDto.setGlHomeHits(homeTeam.path("hits").asInt());
	            lineScoreDto.setGlHomeErrors(homeTeam.path("errors").asInt());
	            lineScoreDto.setGlHomeLob(homeTeam.path("leftOnBase").asInt());
	            lineScoreDto.setGlHomeWin(homeTeam.path("isWinner").asBoolean(false) ? 1 : 0);

	            lineScoreDto.setGlAwayRuns(awayTeam.path("runs").asInt());
	            lineScoreDto.setGlAwayHits(awayTeam.path("hits").asInt());
	            lineScoreDto.setGlAwayErrors(awayTeam.path("errors").asInt());
	            lineScoreDto.setGlAwayLob(awayTeam.path("leftOnBase").asInt());
	            lineScoreDto.setGlAwayWin(awayTeam.path("isWinner").asBoolean(false) ? 1 : 0);

	            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	            lineScoreDto.setGlRegTime(now);
	            lineScoreDto.setGlModTime(now);

	            gameService.insertLineScore(lineScoreDto); // gamelinescore insert

	            // === 2. gameinning INSERT (이닝별 정보) ===
	            for (JsonNode inningNode : innings) {
	                GameDto inningDto = new GameDto();
	                inningDto.setGame_gmSeq(gamePk);
	                inningDto.setGiInning(inningNode.path("num").asInt());
	                inningDto.setGiHomeScore(inningNode.path("home").path("runs").asInt(0));
	                inningDto.setGiAwayScore(inningNode.path("away").path("runs").asInt(0));
	                inningDto.setGiRegTime(now);
	                inningDto.setGiModTime(now);

	                gameService.insertInningScore(inningDto); // gameinning insert
	            }

	            successCount++;

	        } catch (Exception e) {
	            System.err.println("Error inserting for gamePk: " + gamePk);
	            System.err.println("에러 메시지: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    System.out.println("총 처리 경기 수: " + totalCount + ", 성공: " + successCount);

	    return "redirect:/game/gameXdmList";
	}
	
	
	@RequestMapping("/game/GameHofBoardList")
	public String gameHofBoardList(Model model, GameDto dto) {

		return "hof/game/baseball-scoreboard";
	}

	@RequestMapping("/game/GameHofBoardListProc")
	public String gameHofBoardListProc(Model model, GameDto dto) {
		model.addAttribute("list", gameService.selectHofScoreList(dto));
		model.addAttribute("inning", gameService.selectInningByGameSeq(dto));
		return "hof/game/baseball-scoreboardTwo";
	}
	
	@RequestMapping("/game/GameHofMain")
	public String gameHofMain(Model model, GameDto dto) {
		model.addAttribute("item", gameService.selectOneHofScore(dto));
		model.addAttribute("inning", gameService.selectOneInningByGameSeq(dto));
		return "hof/game/baseball_match-main";
	}
	
	@RequestMapping("/game/GameBoxScoreInst")
	public String gameBoxScoreInst(GameDto dto) throws Exception {
	    List<GameDto> list = gameService.listInstForLineScore(dto);

	    for (GameDto game : list) {
	        String gamePk = game.getGame_gmSeq();
	        String apiUrl = "https://statsapi.mlb.com/api/v1/game/" + gamePk + "/boxscore";

	        URL url = new URL(apiUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");

	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line);
	        }
	        br.close();
	        conn.disconnect();

	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode root = mapper.readTree(sb.toString());

	        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	        JsonNode teamsNode = root.path("teams");

	        // 1) gameBoxscore (home, away)
	        for (String side : List.of("home", "away")) {
	            JsonNode teamNode = teamsNode.path(side);
	            GameDto boxScoreDto = new GameDto();

	            boxScoreDto.setGame_gmSeq(gamePk);
	            boxScoreDto.setTeam_tmSeq(teamNode.path("team").path("id").asText());

	            // DTO 필드에 정확히 맞춰서 데이터 넣기
	            boxScoreDto.setGbRuns(teamNode.path("teamStats").path("batting").path("runs").asInt());
	            boxScoreDto.setGbHits(teamNode.path("teamStats").path("batting").path("hits").asInt());
	            boxScoreDto.setGbErrors(teamNode.path("teamStats").path("fielding").path("errors").asInt());
	            boxScoreDto.setGbLob(teamNode.path("teamStats").path("batting").path("leftOnBase").asInt());
	            boxScoreDto.setGbDp(teamNode.path("teamStats").path("batting").path("doublePlays").asInt());
	            boxScoreDto.setGbTrip(teamNode.path("teamStats").path("batting").path("triples").asInt());
	            boxScoreDto.setGbAvg(teamNode.path("teamStats").path("batting").path("avg").asDouble(0.0));
	            boxScoreDto.setGbObp(teamNode.path("teamStats").path("batting").path("obp").asDouble(0.0));
	            boxScoreDto.setGbSlg(teamNode.path("teamStats").path("batting").path("slg").asDouble(0.0));
	            boxScoreDto.setGbOps(teamNode.path("teamStats").path("batting").path("ops").asDouble(0.0));
	            boxScoreDto.setGbSteal(teamNode.path("teamStats").path("batting").path("stolenBases").asInt());
	            boxScoreDto.setGbCauSteal(teamNode.path("teamStats").path("batting").path("caughtStealing").asInt());
	            boxScoreDto.setGbSacBun(teamNode.path("teamStats").path("batting").path("sacrificeBunts").asInt());
	            boxScoreDto.setGbSacFly(teamNode.path("teamStats").path("batting").path("sacrificeFlies").asInt());
	            boxScoreDto.setGbRbi(teamNode.path("teamStats").path("batting").path("rbi").asInt());

	            boxScoreDto.setGbRegTime(now);
	            boxScoreDto.setGbModTime(now);

	            gameService.insertBoxScore(boxScoreDto);
	        }

	        // 2) gameMeta
	        GameDto metaDto = new GameDto();
	        metaDto.setGame_gmSeq(gamePk);
	        metaDto.setGtRegTime(now);
	        metaDto.setGtModTime(now);

	        for (JsonNode info : root.path("info")) {
	            String label = info.path("label").asText();
	            String value = info.path("value").asText();

	            switch (label) {
	                case "Attendance":
	                    metaDto.setGtAttendance(value.isEmpty() ? null : Integer.parseInt(value.replace(",", "")));
	                    break;
	                case "Weather":
	                    metaDto.setGtWeather(value);
	                    break;
	                case "Wind":
	                    metaDto.setGtWind(value);
	                    break;
	            }
	        }

	        for (JsonNode ump : root.path("officials")) {
	            String type = ump.path("officialType").asText();
	            String name = ump.path("official").path("fullName").asText();

	            switch (type) {
	                case "Home Plate":
	                    metaDto.setGtUmpireHome(name);
	                    break;
	                case "First Base":
	                    metaDto.setGtUmpireFirst(name);
	                    break;
	                case "Second Base":
	                    metaDto.setGtUmpireSecond(name);
	                    break;
	                case "Third Base":
	                    metaDto.setGtUmpireThird(name);
	                    break;
	                case "Left Field":
	                    metaDto.setGtUmpireLeft(name);
	                    break;
	                case "Right Field":
	                    metaDto.setGtUmpireRight(name);
	                    break;
	            }
	        }

	        gameService.insertGameMeta(metaDto);

	        // 3) 선수 기록 (batting, pitching, defense)
	        for (String side : List.of("home", "away")) {
	            JsonNode players = teamsNode.path(side).path("players");

	            for (Iterator<String> it = players.fieldNames(); it.hasNext(); ) {
	                String playerId = it.next();
	                JsonNode player = players.path(playerId);

	                // 선수 공통 정보
	                String playerPySeq = playerId;
	                int isStarter = player.path("isStarter").asInt(0);
	                int battingOrder = player.path("battingOrder").asInt(0);

	                // Batting
	                if (player.has("stats") && player.path("stats").has("batting")) {
	                    JsonNode bat = player.path("stats").path("batting");
	                    GameDto batDto = new GameDto();

	                    batDto.setGame_gmSeq(gamePk);
	                    batDto.setPbSeq(playerId);
	                    batDto.setPlayer_pySeq(playerPySeq);
	                    batDto.setPbIsStarter(isStarter);
	                    batDto.setPbBattingOrder(battingOrder);

	                    batDto.setPbAb(bat.path("atBats").asInt());
	                    batDto.setPbRuns(bat.path("runs").asInt());
	                    batDto.setPbHits(bat.path("hits").asInt());
	                    batDto.setPbRbi(bat.path("rbi").asInt());
	                    batDto.setPbBb(bat.path("baseOnBalls").asInt());
	                    batDto.setPbSo(bat.path("strikeOuts").asInt());
	                    batDto.setPbHomerun(bat.path("homeRuns").asInt());
	                    batDto.setPbDouble(bat.path("doubles").asInt());
	                    batDto.setPbTriple(bat.path("triples").asInt());
	                    batDto.setPbSteal(bat.path("stolenBases").asInt());
	                    batDto.setPbCauSteal(bat.path("caughtStealing").asInt());
	                    batDto.setPbSacBunt(bat.path("sacrificeBunts").asInt());
	                    batDto.setPbSacFly(bat.path("sacrificeFlies").asInt());
	                    batDto.setPbAvg(bat.path("avg").asDouble());

	                    batDto.setPbRegTime(now);
	                    batDto.setPbModTime(now);

	                    gameService.insertPlayerBatting(batDto);
	                }

	                // Pitching
	                if (player.has("stats") && player.path("stats").has("pitching")) {
	                    JsonNode pit = player.path("stats").path("pitching");
	                    GameDto pitDto = new GameDto();

	                    pitDto.setGame_gmSeq(gamePk);
	                    pitDto.setPpSeq(playerId);
	                    pitDto.setPlayer_pySeq(playerPySeq);
	                    pitDto.setPpIsStarter(isStarter);

	                    pitDto.setPpIp(pit.path("inningsPitched").asDouble());
	                    pitDto.setPpHit(pit.path("hits").asInt());
	                    pitDto.setPpRun(pit.path("runs").asInt());
	                    pitDto.setPpEaruns(pit.path("earnedRuns").asInt());
	                    pitDto.setPpBb(pit.path("baseOnBalls").asInt());
	                    pitDto.setPpSo(pit.path("strikeOuts").asInt());
	                    pitDto.setPpHr(pit.path("homeRuns").asInt());
	                    pitDto.setPpPitchCount(pit.path("numberOfPitches").asInt());

	                    pitDto.setPpRegTime(now);
	                    pitDto.setPpModTime(now);

	                    gameService.insertPlayerPitching(pitDto);
	                }

	                // Defense
	                if (player.has("stats") && player.path("stats").has("fielding")) {
	                    JsonNode def = player.path("stats").path("fielding");
	                    GameDto defDto = new GameDto();

	                    defDto.setGame_gmSeq(gamePk);
	                    defDto.setPdSeq(playerId);
	                    defDto.setPlayer_pySeq(playerPySeq);

	                    defDto.setPdGames(def.path("gamesPlayed").asInt());
	                    defDto.setPdInnings(def.path("inningsPlayed").asDouble());
	                    defDto.setPdPo(def.path("putOuts").asInt());
	                    defDto.setPdA(def.path("assists").asInt());
	                    defDto.setPdE(def.path("errors").asInt());

	                    defDto.setPdRegTime(now);
	                    defDto.setPdModTime(now);

	                    gameService.insertPlayerDefense(defDto);
	                }
	            }
	        }
	    }

	    return "redirect:/game/gameBoxScoreList";
	}


}


