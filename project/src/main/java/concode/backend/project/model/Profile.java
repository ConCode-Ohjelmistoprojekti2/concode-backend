package concode.backend.project.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    public Profile() {
    }

    public Profile(UUID id, String username) {
        this.id = id;
        this.username = username;
        this.createdAt = OffsetDateTime.now();
    }

    public UUID getId() { return id; }
    public String getUsername() { return username; }
}
