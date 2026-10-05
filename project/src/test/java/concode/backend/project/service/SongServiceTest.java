package concode.backend.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concode.backend.project.dto.ChallengeResponse;
import concode.backend.project.dto.GuessRequest;
import concode.backend.project.dto.GuessResponse;
import tools.jackson.databind.ObjectMapper;

class SongServiceTest {

    private SongService songService;

    @BeforeEach
    void setUp() throws Exception {
        songService = new SongService(new ObjectMapper());
    }

    @Test
    void dailyChallengeContainsExpectedData() {
        ChallengeResponse response = songService.getDailyChallenge();

        assertEquals("daily", response.getType());
        assertNotNull(response.getDate());
        assertNotNull(response.getYoutubeVideoId());
    }

    @Test
    void randomChallengeContainsExpectedData() {
        ChallengeResponse response = songService.getRandomChallenge();

        assertEquals("random", response.getType());
        assertNull(response.getDate());
        assertNotNull(response.getYoutubeVideoId());
    }

    @Test
    void correctGuessMarksTitleAndArtistAsCorrect() {
        GuessRequest request = new GuessRequest(
                "dQw4w9WgXcQ",
                "Never Gonna Give You Up",
                "Rick Astley");

        GuessResponse response = songService.checkGuess(request);

        assertTrue(response.isCorrectTitle());
        assertTrue(response.isCorrectArtist());
        assertTrue(response.isComplete());
    }
}