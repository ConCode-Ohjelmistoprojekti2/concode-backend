package concode.backend.project.dto;

public class GuessResponse {

    private boolean correctTitle;
    private boolean correctArtist;
    private boolean complete;
    private AnswerResponse answer;

    public GuessResponse() {
    }

    public GuessResponse(boolean correctTitle, boolean correctArtist, AnswerResponse answer) {
        this.correctTitle = correctTitle;
        this.correctArtist = correctArtist;
        this.complete = correctTitle && correctArtist;
        this.answer = answer;
    }

    public boolean isCorrectTitle() {
        return correctTitle;
    }

    public void setCorrectTitle(boolean correctTitle) {
        this.correctTitle = correctTitle;
    }

    public boolean isCorrectArtist() {
        return correctArtist;
    }

    public void setCorrectArtist(boolean correctArtist) {
        this.correctArtist = correctArtist;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public AnswerResponse getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerResponse answer) {
        this.answer = answer;
    }

    public static class AnswerResponse {

        private String title;
        private String artist;

        public AnswerResponse() {
        }

        public AnswerResponse(String title, String artist) {
            this.title = title;
            this.artist = artist;
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
    }
}
