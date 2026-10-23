package concode.backend.project.dto;

public class ChallengeResponse {

    private String type;
    private String date;
    private String youtubeVideoId;
    private String genre;
    private Integer releaseYear;

    public ChallengeResponse() {
    }

    public ChallengeResponse(String type, String date, String youtubeVideoId, String genre, Integer releaseYear) {
        this.type = type;
        this.date = date;
        this.youtubeVideoId = youtubeVideoId;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

}
