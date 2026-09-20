package concode.backend.project.dto;

import jakarta.validation.constraints.NotBlank;

public class GuessRequest {

    @NotBlank
    private String youtubeVideoId;

    @NotBlank
    private String titleGuess;

    @NotBlank
    private String artistGuess;

    public GuessRequest() {
    }

    public GuessRequest(String youtubeVideoId, String titleGuess, String artistGuess) {
        this.youtubeVideoId = youtubeVideoId;
        this.titleGuess = titleGuess;
        this.artistGuess = artistGuess;
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
}
