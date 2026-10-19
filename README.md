
 # Music Video Guessing Game
[![Java CI with Maven](https://github.com/ConCode-Ohjelmistoprojekti2/concode-backend/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/ConCode-Ohjelmistoprojekti2/concode-backend/actions/workflows/ci.yml)

## Project idea

Music Video Guessing Game is a music-based guessing game where the player tries to identify songs and artists by watching music videos.

Unlike traditional song guessing games that only play a short audio clip, our game uses the actual music video as part of the gameplay. The player can use both the music and visual clues from the video to find the correct answer.

Music videos will be embedded into the game using YouTube.

## MVP

The minimum viable product will include:

* A playable guessing game with multiple rounds
* Embedded YouTube music videos
* Randomly selected songs
* Guessing the song title and/or artist
* Basic scoring system
* Final score after the game

For the MVP, the songs and their information can be stored in our own database or predefined song list.

## Possible additional features

After the MVP is completed, possible features include:

* Different genres and decades
* Difficulty levels
* Different video clip lengths
* High scores and leaderboard
* User accounts
* Multiplayer mode
* Additional questions, such as release year or genre

## Technologies

The backend uses Java 21 and Spring Boot to provide a REST API. Song data is currently stored locally in a JSON file, so no separate database is used. OpenAPI/Swagger provides API documentation, and YouTube videos are used by the frontend for gameplay.

## Running the application

From the `project` directory, start the backend with:

```bash
./mvnw spring-boot:run
```

The application starts on `http://localhost:8080`.

## Tests

Tests use JUnit and Spring's MockMvc. They check that daily and random challenges contain the expected data, that correct guesses are accepted, that the random-challenge endpoint returns the expected JSON, and that the Spring application context starts.

Run all tests from the `project` directory:

```bash
./mvnw test
```

## Deployment

Rahti deployment: https://concode-backend-git-con-code.2.rahtiapp.fi/

The frontend is deployed on Render and connected to this backend: [concode-frontend.onrender.com](https://concode-frontend.onrender.com). The backend can also be deployed as a Docker container using the included `project/Dockerfile`; it exposes port 8080.

```bash
cd project
docker build -t music-video-game-backend .
docker run -p 8080:8080 music-video-game-backend
```

## Goal

The goal of the project is to create our own version of a music guessing game where the music video itself is an important part of the experience instead of only playing the song audio.


