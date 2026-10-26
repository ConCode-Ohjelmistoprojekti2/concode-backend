package concode.backend.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import concode.backend.project.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByYoutubeVideoId(String youtubeVideoId);
}
