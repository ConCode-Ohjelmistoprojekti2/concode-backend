package concode.backend.project.service;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
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

        boolean correctTitle = matchesWithAllowedTypos(song.getTitle(), guessRequest.getTitleGuess());
        boolean correctArtist = matchesWithAllowedTypos(song.getArtist(), guessRequest.getArtistGuess());
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
        if (value == null) {
            return "";
        }

        return Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^\\p{Alnum}\\s]", "")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private boolean matchesWithAllowedTypos(String expected, String guess) {
        String normalizedExpected = normalize(expected);
        String normalizedGuess = normalize(guess);

        if (normalizedExpected.equals(normalizedGuess)) {
            return true;
        }

        return levenshteinDistance(normalizedExpected, normalizedGuess) <= allowedTypoCount(normalizedExpected.length());
    }

    private int allowedTypoCount(int answerLength) {
        if (answerLength <= 4) {
            return 0;
        }
        if (answerLength <= 8) {
            return 1;
        }
        return 2;
    }

    private int levenshteinDistance(String first, String second) {
        int[] previousRow = new int[second.length() + 1];
        int[] currentRow = new int[second.length() + 1];

        for (int column = 0; column <= second.length(); column++) {
            previousRow[column] = column;
        }

        for (int row = 1; row <= first.length(); row++) {
            currentRow[0] = row;
            for (int column = 1; column <= second.length(); column++) {
                int substitutionCost = first.charAt(row - 1) == second.charAt(column - 1) ? 0 : 1;
                currentRow[column] = Math.min(
                        Math.min(currentRow[column - 1] + 1, previousRow[column] + 1),
                        previousRow[column - 1] + substitutionCost);
            }

            int[] completedRow = previousRow;
            previousRow = currentRow;
            currentRow = completedRow;
        }

        return previousRow[second.length()];
    }
}