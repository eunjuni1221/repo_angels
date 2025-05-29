package com.angels.module.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
	
	@RequestMapping("/game/GameBoxscoreInst")
	public String gameBoxscoreInst(GameDto dto) throws Exception {
	    System.out.println("=== [CALL] GameBoxscoreInst ===");

	    List<GameDto> gameList = gameService.listInstForBoxscore(dto);
	    System.out.println("조회된 경기 수: " + gameList.size());

	    int totalCount = 0;
	    int successCount = 0;

	    for (GameDto game : gameList) {
	        totalCount++;
	        Long gamePk = game.getGmSeq();  // gmSeq는 Long 타입
	        String apiUrl = "https://statsapi.mlb.com/api/v1/game/" + gamePk + "/boxscore";

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

	            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

	            // 1. GameBoxscore insert
	            JsonNode teams = root.path("teams");
	            for (String side : Arrays.asList("home", "away")) {
	                JsonNode teamNode = teams.path(side);

	                GameDto boxDto = new GameDto();
	                boxDto.setGame_gmSeq(gamePk);
	                boxDto.setTeam_tmSeq(teamNode.path("team").path("id").asLong());

	                JsonNode batting = teamNode.path("teamStats").path("batting");
	                JsonNode fielding = teamNode.path("teamStats").path("fielding");

	                boxDto.setGbRuns(batting.path("runs").asInt());
	                boxDto.setGbHits(batting.path("hits").asInt());
	                boxDto.setGbErrors(fielding.path("errors").asInt());
	                boxDto.setGbLob(batting.path("leftOnBase").asInt());
	                boxDto.setGbDp(fielding.path("doublePlays").asInt());
	                boxDto.setGbTrip(batting.path("triples").asInt());
	                boxDto.setGbAvg(batting.path("avg").asDouble());
	                boxDto.setGbObp(batting.path("obp").asDouble());
	                boxDto.setGbSlg(batting.path("slg").asDouble());
	                boxDto.setGbOps(batting.path("ops").asDouble());
	                boxDto.setGbSteal(batting.path("stolenBases").asInt());
	                boxDto.setGbCauSteal(batting.path("caughtStealing").asInt());
	                boxDto.setGbSacBun(batting.path("sacBunts").asInt());
	                boxDto.setGbSacFly(batting.path("sacFlies").asInt());
	                boxDto.setGbRbi(batting.path("rbi").asInt());

	                boxDto.setGbRegTime(now);
	                boxDto.setGbModTime(now);

	                gameService.insertGameBoxscore(boxDto);
	            }

	            // 2. GameMeta insert
	            GameDto metaDto = new GameDto();
	            metaDto.setGame_gmSeq(gamePk);
	            metaDto.setGtAttendance(parseInfo(root.path("info"), "Attendance"));
	            metaDto.setGtWeather(parseInfo(root.path("info"), "Weather"));
	            metaDto.setGtWind(parseInfo(root.path("info"), "Wind"));

	            metaDto.setGtUmpireHome(getUmpire(root, "Home Plate"));
	            metaDto.setGtUmpireFirst(getUmpire(root, "First Base"));
	            metaDto.setGtUmpireSecond(getUmpire(root, "Second Base"));
	            metaDto.setGtUmpireThird(getUmpire(root, "Third Base"));
	            metaDto.setGtUmpireLeft(getUmpire(root, "Left Field"));
	            metaDto.setGtUmpireRight(getUmpire(root, "Right Field"));
	            metaDto.setGtRegTime(now);
	            metaDto.setGtModTime(now);

	            gameService.insertGameMeta(metaDto);

	            // 3. Players
	            JsonNode players = root.path("players");
	            for (Iterator<String> it = players.fieldNames(); it.hasNext(); ) {
	                String playerKey = it.next();
	                JsonNode playerNode = players.path(playerKey);
	                String pySeq = playerKey.replace("ID", "");  // playerId

	                JsonNode stats = playerNode.path("stats");

	                // 3-1. Batting
	                if (stats.has("batting")) {
	                    GameDto batDto = new GameDto();
	                    batDto.setGame_gmSeq(gamePk);
	                    batDto.setPlayer_pySeq(Long.parseLong(pySeq));
	                    batDto.setPbIsStarter(playerNode.path("position").has("code") ? 1 : 0);
	                    batDto.setPbBattingOrder(playerNode.path("battingOrder").asInt(0));
	                    batDto.setPbAb(stats.path("batting").path("atBats").asInt());
	                    batDto.setPbRuns(stats.path("batting").path("runs").asInt());
	                    batDto.setPbHits(stats.path("batting").path("hits").asInt());
	                    batDto.setPbRbi(stats.path("batting").path("rbi").asInt());
	                    batDto.setPbBb(stats.path("batting").path("baseOnBalls").asInt());
	                    batDto.setPbSo(stats.path("batting").path("strikeOuts").asInt());
	                    batDto.setPbHomerun(stats.path("batting").path("homeRuns").asInt());
	                    batDto.setPbDouble(stats.path("batting").path("doubles").asInt());
	                    batDto.setPbTriple(stats.path("batting").path("triples").asInt());
	                    batDto.setPbSteal(stats.path("batting").path("stolenBases").asInt());
	                    batDto.setPbCauSteal(stats.path("batting").path("caughtStealing").asInt());
	                    batDto.setPbSacBunt(stats.path("batting").path("sacBunts").asInt());
	                    batDto.setPbSacFly(stats.path("batting").path("sacFlies").asInt());
	                    batDto.setPbAvg(stats.path("batting").path("avg").asDouble());
	                    batDto.setPbRegTime(now);
	                    batDto.setPbModTime(now);

	                    gameService.insertPlayerBatting(batDto);
	                }

	                // 3-2. Pitching
	                if (stats.has("pitching")) {
	                    GameDto pitchDto = new GameDto();
	                    pitchDto.setGame_gmSeq(gamePk);
	                    pitchDto.setPlayer_pySeq(Long.parseLong(pySeq));
	                    pitchDto.setPpIsStarter(playerNode.path("position").has("code") ? 1 : 0);
	                    pitchDto.setPpIp(stats.path("pitching").path("inningsPitched").asDouble());
	                    pitchDto.setPpHit(stats.path("pitching").path("hits").asInt());
	                    pitchDto.setPpRun(stats.path("pitching").path("runs").asInt());
	                    pitchDto.setPpEaruns(stats.path("pitching").path("earnedRuns").asInt());
	                    pitchDto.setPpBb(stats.path("pitching").path("baseOnBalls").asInt());
	                    pitchDto.setPpSo(stats.path("pitching").path("strikeOuts").asInt());
	                    pitchDto.setPpHr(stats.path("pitching").path("homeRuns").asInt());
	                    pitchDto.setPpPitchCount(stats.path("pitching").path("numberOfPitches").asInt());
	                    pitchDto.setPpStrike(stats.path("pitching").path("strikes").asInt());
	                    pitchDto.setPpEra(stats.path("pitching").path("era").asDouble());
	                    pitchDto.setPpRegTime(now);
	                    pitchDto.setPpModtime(now);

	                    gameService.insertPlayerPitching(pitchDto);
	                }

	                // 3-3. Defense
	                if (stats.has("fielding")) {
	                    GameDto defDto = new GameDto();
	                    defDto.setGame_gmSeq(gamePk);
	                    defDto.setPlayer_pySeq(Long.parseLong(pySeq));
	                    defDto.setPdPosition(playerNode.path("position").path("abbreviation").asText());
	                    defDto.setPdError(stats.path("fielding").path("errors").asInt());
	                    gameService.insertPlayerDefense(defDto);
	                }
	            }

	            successCount++;

	        } catch (Exception e) {
	            System.err.println("Error inserting for gamePk: " + gamePk);
	            e.printStackTrace();
	        }
	    }

	    System.out.println("총 처리 경기 수: " + totalCount + ", 성공: " + successCount);

	    return "redirect:/game/gameXdmList";
	}

}


