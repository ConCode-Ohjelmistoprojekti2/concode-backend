package concode.backend.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import concode.backend.project.model.Score;
import concode.backend.project.model.ScoreMode;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findTop10ByModeAndChallengeDateOrderByScoreDescCreatedAtAsc(ScoreMode mode, LocalDate challengeDate);

    List<Score> findTop10ByModeOrderByScoreDescCreatedAtAsc(ScoreMode mode);
}
