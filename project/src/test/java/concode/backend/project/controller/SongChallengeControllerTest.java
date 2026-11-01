package concode.backend.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import concode.backend.project.dto.ChallengeResponse;
import concode.backend.project.model.Song;
import concode.backend.project.repository.SongRepository;
import concode.backend.project.service.SongService;

public class SongChallengeControllerTest {

        @Test
        void dailyChallengeReturnsExpectedData() throws Exception {
                SongRepository songRepository = mock(SongRepository.class);
                when(songRepository.count()).thenReturn(1L);
                when(songRepository.findAll(any(Pageable.class)))
                                .thenReturn(new PageImpl<>(
                                                List.of(new Song("Title", "Artist", "test-video-id", "Pop", 1987))));

                SongService songService = new SongService(songRepository);
                ChallengeController controller = new ChallengeController(songService);

                ChallengeResponse response = controller.getDailyChallenge();

                assertEquals("daily", response.getType());
                assertNotNull(response.getDate());
                assertEquals("test-video-id", response.getYoutubeVideoId());
        }

        @Test
        void randomEndpointReturnsSuccessfulRandomChallengeJson() throws Exception {
                SongService songService = mock(SongService.class);
                when(songService.getRandomChallenge())
                                .thenReturn(new ChallengeResponse("random", null, "test-video-id", "Pop", 1987));
                MockMvc mockMvc = MockMvcBuilders.standaloneSetup(
                                new ChallengeController(songService)).build();

                mockMvc.perform(get("/api/challenges/random"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.type").value("random"))
                                .andExpect(jsonPath("$.youtubeVideoId").value("test-video-id"));
        }
}