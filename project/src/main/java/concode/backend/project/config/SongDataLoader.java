package concode.backend.project.config;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import concode.backend.project.model.Song;
import concode.backend.project.repository.SongRepository;
import tools.jackson.databind.ObjectMapper;

@Component
public class SongDataLoader implements CommandLineRunner {

    private final SongRepository songRepository;
    private final ObjectMapper objectMapper;

    public SongDataLoader(SongRepository songRepository, ObjectMapper objectMapper) {
        this.songRepository = songRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (songRepository.count() > 0) {
            return;
        }

        InputStream inputStream = getClass().getResourceAsStream("/videos.json");
        Objects.requireNonNull(inputStream, "videos.json was not found in resources");

        List<Song> songs = Arrays.asList(objectMapper.readValue(inputStream, Song[].class));
        songRepository.saveAll(songs);
    }
}
