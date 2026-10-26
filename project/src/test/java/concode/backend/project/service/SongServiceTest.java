package concode.backend.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import concode.backend.project.dto.ChallengeResponse;
import concode.backend.project.dto.GuessRequest;
import concode.backend.project.dto.GuessResponse;
import concode.backend.project.model.Song;
import concode.backend.project.repository.SongRepository;

class SongServiceTest {

    private SongService songService;

    @BeforeEach
    void setUp() {
        SongRepository songRepository = mock(SongRepository.class);
        Song song = new Song("Never Gonna Give You Up", "Rick Astley", "dQw4w9WgXcQ", "Pop", 1987);

        when(songRepository.count()).thenReturn(1L);
        when(songRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(song)));
        when(songRepository.findByYoutubeVideoId("dQw4w9WgXcQ")).thenReturn(Optional.of(song));

        songService = new SongService(songRepository);
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
                "Rick Astley",
                "pop",
                1987);

        GuessResponse response = songService.checkGuess(request);

        assertTrue(response.isCorrectTitle());
        assertTrue(response.isCorrectArtist());
        assertTrue(response.isComplete());
        assertTrue(response.isCorrectGenre());
        assertTrue(response.isCorrectYear());
    }
}