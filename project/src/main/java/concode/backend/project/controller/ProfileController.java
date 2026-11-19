package concode.backend.project.controller;

import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import concode.backend.project.dto.ProfileRequest;
import concode.backend.project.dto.ProfileResponse;
import concode.backend.project.service.ProfileService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) { this.profileService = profileService; }

    @PostMapping
    public ProfileResponse create(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody ProfileRequest request) {
        return profileService.create(UUID.fromString(jwt.getSubject()), request);
    }
}
