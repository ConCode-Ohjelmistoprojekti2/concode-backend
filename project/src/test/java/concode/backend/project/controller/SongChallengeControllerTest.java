package concode.backend.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import concode.backend.project.dto.ChallengeResponse;
import concode.backend.project.service.SongService;
import tools.jackson.databind.ObjectMapper;

public class SongChallengeControllerTest {

    @Test
    void dailyChallengeReturnsExpectedData() throws Exception {
        SongService songService = new SongService(new ObjectMapper());
        ChallengeController controller = new ChallengeController(songService);

        ChallengeResponse response = controller.getDailyChallenge();

        assertEquals("daily", response.getType());
        assertNotNull(response.getDate());
        assertNotNull(response.getYoutubeVideoId());
    }
}