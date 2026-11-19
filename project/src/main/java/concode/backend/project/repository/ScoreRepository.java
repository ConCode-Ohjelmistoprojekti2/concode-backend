package concode.backend.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import concode.backend.project.model.Score;
import concode.backend.project.model.ScoreMode;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findTop10ByModeAndChallengeDateOrderByScoreDescCreatedAtAsc(ScoreMode mode, LocalDate challengeDate);

    List<Score> findTop10ByModeOrderByScoreDescCreatedAtAsc(ScoreMode mode);

    boolean existsByUserIdAndModeAndChallengeDate(java.util.UUID userId, ScoreMode mode, LocalDate challengeDate);

    @Query(value = """
            SELECT * FROM (
                SELECT DISTINCT ON (user_id) *
                FROM scores
                WHERE mode = 'RANDOM' AND user_id IS NOT NULL
                ORDER BY user_id, score DESC, created_at ASC
            ) AS best_scores
            ORDER BY score DESC, created_at ASC
            LIMIT 10
            """, nativeQuery = true)
    List<Score> findTop10RandomLeaderboardByBestScorePerUser();
}
