package concode.backend.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GuessRequest {

    @NotBlank
    private String youtubeVideoId;

    @NotBlank
    private String titleGuess;

    @NotBlank
    private String artistGuess;

    @NotBlank
    String genreGuess;

    @NotNull
    Integer releaseYear;

    public GuessRequest() {
    }

    public GuessRequest(String youtubeVideoId, String titleGuess, String artistGuess, String genreGuess,
            Integer releaseYearGuess) {
        this.youtubeVideoId = youtubeVideoId;
        this.titleGuess = titleGuess;
        this.artistGuess = artistGuess;
        this.genreGuess = genreGuess;
        this.releaseYear = releaseYearGuess;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public String getTitleGuess() {
        return titleGuess;
    }

    public void setTitleGuess(String titleGuess) {
        this.titleGuess = titleGuess;
    }

    public String getArtistGuess() {
        return artistGuess;
    }

    public void setArtistGuess(String artistGuess) {
        this.artistGuess = artistGuess;
    }

    public String getGenreGuess() {
        return genreGuess;
    }

    public void setGenreGuess(String genreGuess) {
        this.genreGuess = genreGuess;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
}
