package concode.backend.project.service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import tools.jackson.databind.ObjectMapper;

import concode.backend.project.model.Song;

@Service
public class SongService {

    private final List<Song> songs;
    private final Random random = new Random();

    public SongService(ObjectMapper objectMapper) throws Exception {
        InputStream inputStream =
                getClass().getResourceAsStream("/videos.json");

        Song[] songArray =
                objectMapper.readValue(inputStream, Song[].class);

        songs = Arrays.asList(songArray);
    }

    public Song getRandomSong() {
        return songs.get(random.nextInt(songs.size()));
    }
}