package concode.backend.project.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import concode.backend.project.dto.ChallengeResponse;
import concode.backend.project.dto.GuessRequest;
import concode.backend.project.dto.GuessResponse;
import concode.backend.project.dto.GuessResponse.AnswerResponse;
import concode.backend.project.model.Song;
import concode.backend.project.repository.SongRepository;

@Service
public class SongService {

    private static final ZoneId DAILY_CHALLENGE_ZONE = ZoneId.of("Europe/Helsinki");

    private final SongRepository songRepository;
    private final Random random = new Random();

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;

    }

    public ChallengeResponse getRandomChallenge() {
        Song song = getRandomSong();
        return toChallengeResponse("random", null, song);
    }

    public ChallengeResponse getDailyChallenge() {
        LocalDate today = LocalDate.now(DAILY_CHALLENGE_ZONE);
        Song song = getDailySong(today);

        return toChallengeResponse("daily", today.toString(), song);
    }

    public GuessResponse checkGuess(GuessRequest guessRequest) {
        Song song = findByYoutubeVideoId(guessRequest.getYoutubeVideoId());

        boolean correctTitle = normalize(song.getTitle()).equals(normalize(guessRequest.getTitleGuess()));
        boolean correctArtist = normalize(song.getArtist()).equals(normalize(guessRequest.getArtistGuess()));
        boolean correctGenre = normalize(song.getGenre()).equals(normalize(guessRequest.getGenreGuess()));
        boolean correctYear = song.getYear() != null
                && song.getYear().equals(guessRequest.getYearGuess());

        AnswerResponse answer = new AnswerResponse(song.getTitle(), song.getArtist(), song.getYear(), song.getGenre());

        GuessResponse response = new GuessResponse(correctTitle, correctArtist, correctGenre, correctYear, answer);
        boolean genreOk = guessRequest.getGenreGuess() == null || correctGenre;
        boolean yearOk = guessRequest.getYearGuess() == null || correctYear;
        response.setComplete(correctTitle && correctArtist && genreOk && yearOk);
        return response;
    }

    private ChallengeResponse toChallengeResponse(String type, String date, Song song) {
        return new ChallengeResponse(type, date, song.getYoutubeVideoId(),
                song.getGenre(),
                song.getYear());
    }

    private Song getRandomSong() {
        int songIndex = random.nextInt(getSongCount());
        return getSongAt(songIndex);
    }

    private Song getDailySong(LocalDate date) {
        int songIndex = (int) Math.floorMod(date.toEpochDay(), (long) getSongCount());
        return getSongAt(songIndex);
    }

    private int getSongCount() {
        long count = songRepository.count();

        if (count == 0) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "No songs in database");
        }

        return (int) count;
    }

    private Song getSongAt(int index) {
        return songRepository.findAll(PageRequest.of(index, 1, Sort.by("id")))
                .getContent()
                .get(0);
    }

    private Song findByYoutubeVideoId(String youtubeVideoId) {
        return songRepository.findByYoutubeVideoId(youtubeVideoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown YouTube video id"));
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }
}