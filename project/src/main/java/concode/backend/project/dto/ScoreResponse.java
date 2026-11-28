package concode.backend.project.dto;

import java.time.OffsetDateTime;
import java.math.BigDecimal;

import concode.backend.project.model.Score;

public class ScoreResponse {

    private String playerName;
    private BigDecimal score;
    private OffsetDateTime createdAt;

    public ScoreResponse(Score score) {
        this.playerName = score.getPlayerName();
        this.score = score.getScore();
        this.createdAt = score.getCreatedAt();
    }

    public String getPlayerName() {
        return playerName;
    }

    public BigDecimal getScore() {
        return score;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
