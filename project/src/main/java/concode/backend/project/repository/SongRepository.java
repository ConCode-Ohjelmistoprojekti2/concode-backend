package concode.backend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import concode.backend.project.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
}
