package concode.backend.project.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import concode.backend.project.dto.ProfileRequest;
import concode.backend.project.dto.ProfileResponse;
import concode.backend.project.model.Profile;
import concode.backend.project.repository.ProfileRepository;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileResponse create(UUID userId, ProfileRequest request) {
        if (profileRepository.existsById(userId)) {
            return new ProfileResponse(profileRepository.findById(userId).orElseThrow());
        }

        String username = request.getUsername().trim();
        if (profileRepository.existsByUsernameIgnoreCase(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "That username is already taken");
        }
        return new ProfileResponse(profileRepository.save(new Profile(userId, username)));
    }

    public String getUsername(UUID userId) {
        return profileRepository.findById(userId)
                .map(Profile::getUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Create a profile before saving a score"));
    }
}
