package concode.backend.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProfileRequest {
    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z0-9_-]+", message = "Username may only contain letters, numbers, underscores and dashes")
    private String username;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
