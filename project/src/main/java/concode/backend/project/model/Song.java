package concode.backend.project.model;

public class Song {

    private String title;
    private String artist;
    private String youtubeVideoId;

    public Song() {
    }

    public Song(String title, String artist, String youtubeVideoId) {
        this.title = title;
        this.artist = artist;
        this.youtubeVideoId = youtubeVideoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }
}