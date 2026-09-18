package concode.backend.project.dto;

public class ChallengeResponse {

    private String type;
    private String date;
    private String youtubeVideoId;

    public ChallengeResponse() {
    }

    public ChallengeResponse(String type, String date, String youtubeVideoId) {
        this.type = type;
        this.date = date;
        this.youtubeVideoId = youtubeVideoId;
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
}
