package concode.backend.project.service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tools.jackson.databind.ObjectMapper;

import concode.backend.project.dto.ChallengeResponse;
import concode.backend.project.dto.GuessRequest;
import concode.backend.project.dto.GuessResponse;
import concode.backend.project.dto.GuessResponse.AnswerResponse;
import concode.backend.project.model.Song;

@Service
public class SongService {

    private static final ZoneId DAILY_CHALLENGE_ZONE = ZoneId.of("Europe/Helsinki");

    private final List<Song> songs;
    private final Random random = new Random();

    public SongService(ObjectMapper objectMapper) throws Exception {
        InputStream inputStream =
                getClass().getResourceAsStream("/videos.json");

        Objects.requireNonNull(inputStream, "videos.json was not found in resources");

        Song[] songArray =
                objectMapper.readValue(inputStream, Song[].class);

        songs = Arrays.asList(songArray);
    }

    public Song getRandomSong() {
        return songs.get(random.nextInt(songs.size()));
    }

    public ChallengeResponse getRandomChallenge() {
        Song song = getRandomSong();
        return toChallengeResponse("random", null, song);
    }

    public ChallengeResponse getDailyChallenge() {
        LocalDate today = LocalDate.now(DAILY_CHALLENGE_ZONE);
        int songIndex = Math.floorMod(today.toEpochDay(), songs.size());
        Song song = songs.get(songIndex);

        return toChallengeResponse("daily", today.toString(), song);
    }

    public GuessResponse checkGuess(GuessRequest guessRequest) {
        Song song = findByYoutubeVideoId(guessRequest.getYoutubeVideoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown YouTube video id"));

        boolean correctTitle = normalize(song.getTitle()).equals(normalize(guessRequest.getTitleGuess()));
        boolean correctArtist = normalize(song.getArtist()).equals(normalize(guessRequest.getArtistGuess()));
        AnswerResponse answer = correctTitle && correctArtist
                ? new AnswerResponse(song.getTitle(), song.getArtist())
                : null;

        return new GuessResponse(correctTitle, correctArtist, answer);
    }

    private ChallengeResponse toChallengeResponse(String type, String date, Song song) {
        return new ChallengeResponse(type, date, song.getYoutubeVideoId());
    }

    private Optional<Song> findByYoutubeVideoId(String youtubeVideoId) {
        return songs.stream()
                .filter(song -> song.getYoutubeVideoId().equals(youtubeVideoId))
                .findFirst();
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }
}
