package concode.backend.project.dto;

import java.time.OffsetDateTime;

import concode.backend.project.model.Score;

public class ScoreResponse {

    private String playerName;
    private int score;
    private OffsetDateTime createdAt;

    public ScoreResponse(Score score) {
        this.playerName = score.getPlayerName();
        this.score = score.getScore();
        this.createdAt = score.getCreatedAt();
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}