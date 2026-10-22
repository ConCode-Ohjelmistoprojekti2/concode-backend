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

    @NotNull
    private Integer yearGuess;

    @NotBlank
    private String genreGuess;

    public GuessRequest() {
    }

    public GuessRequest(String youtubeVideoId, String titleGuess, String artistGuess) {
        this.youtubeVideoId = youtubeVideoId;
        this.titleGuess = titleGuess;
        this.artistGuess = artistGuess;
    }

    public GuessRequest(String youtubeVideoId, String titleGuess, String artistGuess,
            Integer yearGuess, String genreGuess) {
        this.youtubeVideoId = youtubeVideoId;
        this.titleGuess = titleGuess;
        this.artistGuess = artistGuess;
        this.yearGuess = yearGuess;
        this.genreGuess = genreGuess;
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

    public Integer getYearGuess() {
        return yearGuess;
    }

    public void setYearGuess(Integer yearGuess) {
        this.yearGuess = yearGuess;
    }

    public String getGenreGuess() {
        return genreGuess;
    }

    public void setGenreGuess(String genreGuess) {
        this.genreGuess = genreGuess;
    }
}
