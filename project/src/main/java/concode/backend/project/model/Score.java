package concode.backend.project.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String playerName;

    @Column(nullable = false)
    private int score;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ScoreMode mode;

    private LocalDate challengeDate;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    public Score() {
    }

    public Score(String playerName, int score, ScoreMode mode, LocalDate challengeDate) {
        this.playerName = playerName;
        this.score = score;
        this.mode = mode;
        this.challengeDate = challengeDate;
        this.createdAt = OffsetDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public ScoreMode getMode() {
        return mode;
    }

    public LocalDate getChallengeDate() {
        return challengeDate;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}