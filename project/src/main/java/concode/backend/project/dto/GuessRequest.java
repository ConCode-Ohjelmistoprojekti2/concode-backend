package concode.backend.project.dto;

import jakarta.validation.constraints.NotBlank;

public class GuessRequest {

    @NotBlank
    private String youtubeVideoId;

    @NotBlank
    private String titleGuess;

    @NotBlank
    private String artistGuess;

    private String genreGuess;

    private Integer yearGuess;

    public GuessRequest() {
    }

    public GuessRequest(String youtubeVideoId, String titleGuess, String artistGuess, String genreGuess,
            Integer yearGuess) {
        this.youtubeVideoId = youtubeVideoId;
        this.titleGuess = titleGuess;
        this.artistGuess = artistGuess;
        this.genreGuess = genreGuess;
        this.yearGuess = yearGuess;
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

    public Integer getYearGuess() {
        return yearGuess;
    }

    public void setYearGuess(Integer yearGuess) {
        this.yearGuess = yearGuess;
    }
}
