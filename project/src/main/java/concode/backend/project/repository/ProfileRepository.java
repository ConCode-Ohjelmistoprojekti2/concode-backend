package concode.backend.project.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import concode.backend.project.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    boolean existsByUsernameIgnoreCase(String username);
    Optional<Profile> findById(UUID id);
}
