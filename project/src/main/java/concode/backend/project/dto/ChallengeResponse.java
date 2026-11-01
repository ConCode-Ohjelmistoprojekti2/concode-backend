package concode.backend.project.dto;

public class ChallengeResponse {

    private String type;
    private String date;
    private String youtubeVideoId;
    private String genre;
    private Integer year;

    public ChallengeResponse() {
    }

    public ChallengeResponse(String type, String date, String youtubeVideoId, String genre, Integer year) {
        this.type = type;
        this.date = date;
        this.youtubeVideoId = youtubeVideoId;
        this.genre = genre;
        this.year = year;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
