package concode.backend.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import concode.backend.project.dto.ScoreRequest;
import concode.backend.project.dto.ScoreResponse;
import concode.backend.project.model.Score;
import concode.backend.project.model.ScoreMode;
import concode.backend.project.repository.ScoreRepository;

class ScoreServiceTest {

    @Test
    void savesDailyScoreForToday() {
        ScoreRepository repository = mock(ScoreRepository.class);
        when(repository.save(any(Score.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ProfileService profiles = mock(ProfileService.class);
        when(profiles.getUsername(any())).thenReturn("Ada");
        ScoreService scoreService = new ScoreService(repository, profiles);
        ScoreRequest request = request("2.28", "daily");

        ScoreResponse response = scoreService.saveScore(request, java.util.UUID.randomUUID());

        assertEquals("Ada", response.getPlayerName());
        assertEquals(new BigDecimal("2.28"), response.getScore());
        verify(repository).save(any(Score.class));
    }

    @Test
    void returnsDailyScoresForToday() {
        ScoreRepository repository = mock(ScoreRepository.class);
        Score savedScore = new Score(java.util.UUID.randomUUID(), "Ada", new BigDecimal("2.28"), ScoreMode.DAILY, LocalDate.now());
        when(repository.findTop10ByModeAndChallengeDateOrderByScoreDescCreatedAtAsc(
                any(ScoreMode.class), any(LocalDate.class))).thenReturn(List.of(savedScore));
        ScoreService scoreService = new ScoreService(repository, mock(ProfileService.class));

        List<ScoreResponse> leaderboard = scoreService.getDailyLeaderboard();

        assertEquals(1, leaderboard.size());
        assertEquals("Ada", leaderboard.getFirst().getPlayerName());
        assertEquals(new BigDecimal("2.28"), leaderboard.getFirst().getScore());
    }

    @Test
    void rejectsUnknownMode() {
        ScoreService scoreService = new ScoreService(mock(ScoreRepository.class), mock(ProfileService.class));

        assertThrows(ResponseStatusException.class, () -> scoreService.saveScore(request("8", "WEEKLY"), java.util.UUID.randomUUID()));
    }

    private ScoreRequest request(String score, String mode) {
        ScoreRequest request = new ScoreRequest();
        request.setScore(new BigDecimal(score));
        request.setMode(mode);
        return request;
    }
}
