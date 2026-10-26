package concode.backend.project.dto;

public class GuessResponse {

    private boolean correctTitle;
    private boolean correctArtist;
    private boolean correctGenre;
    private boolean correctYear;
    private boolean complete;
    private AnswerResponse answer;

    public GuessResponse() {
    }

    public GuessResponse(boolean correctTitle, boolean correctArtist,     boolean correctGenre, boolean correctYear,
            AnswerResponse answer) {
        this.correctTitle = correctTitle;
        this.correctArtist = correctArtist;
        this.correctGenre = correctGenre;
            this.correctYear = correctYear;
            this.complete = correctTitle && correctArtist && correctGenre && correctYear;
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
        private Integer year;
        private String genre;

        public AnswerResponse() {
        }

        public AnswerResponse(String title, String artist, Integer year, String genre) {
            this.title = title;
            this.artist = artist;
            this.year = year;
            this.genre = genre;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
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

    public boolean isCorrectGenre() {
        return correctGenre;
    }

    public void setCorrectGenre(boolean correctGenre) {
        this.correctGenre = correctGenre;
    }

    public boolean isCorrectYear() {
        return correctYear;
    }

    public void setCorrectYear(boolean correctYear) {
        this.correctYear = correctYear;
    }

}
