package concode.backend.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    @Test
    void randomEndpointReturnsSuccessfulRandomChallengeJson() throws Exception {
        SongService songService = mock(SongService.class);
        when(songService.getRandomChallenge())
                .thenReturn(new ChallengeResponse("random", null, "test-video-id"));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(
                new ChallengeController(songService)).build();

        mockMvc.perform(get("/api/challenges/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("random"))
                .andExpect(jsonPath("$.youtubeVideoId").value("test-video-id"));
    }
}