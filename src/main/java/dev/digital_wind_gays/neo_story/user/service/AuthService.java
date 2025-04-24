package dev.digital_wind_gays.neo_story.user.service;

import dev.digital_wind_gays.neo_story.user.dto.JwtDto;
import dev.digital_wind_gays.neo_story.user.dto.LoginDto;
import dev.digital_wind_gays.neo_story.user.dto.RegisterDto;
import jakarta.validation.Valid;

public interface AuthService {

    JwtDto login(@Valid LoginDto loginDto);

    JwtDto register(@Valid RegisterDto registerDto);
}
