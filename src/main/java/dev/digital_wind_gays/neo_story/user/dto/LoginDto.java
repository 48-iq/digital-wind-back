package dev.digital_wind_gays.neo_story.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    @NotNull
    @Pattern(regexp = "^\\w{3,32}$")
    private String username;
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z_])(?=.*[A-Z])(?=.*[@#$%^&+=!*:?()\\[\\]])(?=\\S+$).{8,}$")
    private String password;
}
