package concode.backend.project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import concode.backend.project.dto.ChallengeResponse;
import concode.backend.project.dto.GuessRequest;
import concode.backend.project.dto.GuessResponse;
import concode.backend.project.service.SongService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/challenges")
@CrossOrigin(origins = "http://localhost:5173")
public class ChallengeController {

    private final SongService songService;

    public ChallengeController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/daily")
    public ChallengeResponse getDailyChallenge() {
        return songService.getDailyChallenge();
    }

    @GetMapping("/random")
    public ChallengeResponse getRandomChallenge() {
        return songService.getRandomChallenge();
    }

    @PostMapping("/guess")
    public GuessResponse checkGuess(@Valid @RequestBody GuessRequest guessRequest) {
        return songService.checkGuess(guessRequest);
    }
}
