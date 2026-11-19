package concode.backend.project.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import concode.backend.project.dto.ScoreRequest;
import concode.backend.project.dto.ScoreResponse;
import concode.backend.project.model.Score;
import concode.backend.project.model.ScoreMode;
import concode.backend.project.repository.ScoreRepository;

@Service
public class ScoreService {

    private static final ZoneId DAILY_CHALLENGE_ZONE = ZoneId.of("Europe/Helsinki");

    private final ScoreRepository scoreRepository;
    private final ProfileService profileService;

    public ScoreService(ScoreRepository scoreRepository, ProfileService profileService) {
        this.scoreRepository = scoreRepository;
        this.profileService = profileService;
    }

    public ScoreResponse saveScore(ScoreRequest request, UUID userId) {
        ScoreMode mode = parseMode(request.getMode());
        LocalDate challengeDate = LocalDate.now(DAILY_CHALLENGE_ZONE);
        if (scoreRepository.existsByUserIdAndModeAndChallengeDate(userId, mode, challengeDate)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "You have already saved a " + mode.name().toLowerCase(Locale.ROOT) + " score today");
        }

        Score score = new Score(userId, profileService.getUsername(userId), request.getScore(), mode, challengeDate);

        return new ScoreResponse(scoreRepository.save(score));
    }

    public List<ScoreResponse> getDailyLeaderboard() {
        return scoreRepository.findTop10ByModeAndChallengeDateOrderByScoreDescCreatedAtAsc(
                ScoreMode.DAILY, LocalDate.now(DAILY_CHALLENGE_ZONE)).stream()
                .map(ScoreResponse::new)
                .toList();
    }

    public List<ScoreResponse> getRandomLeaderboard() {
        return scoreRepository.findTop10RandomLeaderboardByBestScorePerUser().stream()
                .map(ScoreResponse::new)
                .toList();
    }

    private ScoreMode parseMode(String value) {
        try {
            return ScoreMode.valueOf(value.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mode must be DAILY or RANDOM");
        }
    }
}
