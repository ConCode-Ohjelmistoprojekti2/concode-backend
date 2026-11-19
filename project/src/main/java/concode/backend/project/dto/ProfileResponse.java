package concode.backend.project.dto;

import concode.backend.project.model.Profile;

public record ProfileResponse(String username) {
    public ProfileResponse(Profile profile) {
        this(profile.getUsername());
    }
}
