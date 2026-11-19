package concode.backend.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

import concode.backend.project.dto.ScoreRequest;
import concode.backend.project.dto.ScoreResponse;
import concode.backend.project.service.ScoreService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping
    public ScoreResponse saveScore(@Valid @RequestBody ScoreRequest request,
            @AuthenticationPrincipal Jwt jwt) {
        return scoreService.saveScore(request, java.util.UUID.fromString(jwt.getSubject()));
    }

    @GetMapping("/leaderboard/daily")
    public List<ScoreResponse> getDailyLeaderboard() {
        return scoreService.getDailyLeaderboard();
    }

    @GetMapping("/leaderboard/random")
    public List<ScoreResponse> getRandomLeaderboard() {
        return scoreService.getRandomLeaderboard();
    }
}
