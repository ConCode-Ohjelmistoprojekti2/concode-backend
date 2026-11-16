package concode.backend.project.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import concode.backend.project.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByYoutubeVideoId(String youtubeVideoId);

    @Query("SELECT DISTINCT s.genre FROM Song s WHERE s.genre IS NOT NULL ORDER BY s.genre")
    List<String> findAllDistinctGenres();
}
