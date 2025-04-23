package dev.digital_wind_gays.neo_story.user.services;

import dev.digital_wind_gays.neo_story.user.dto.JwtDto;
import dev.digital_wind_gays.neo_story.user.dto.LoginDto;
import dev.digital_wind_gays.neo_story.user.dto.RegisterDto;

public interface AuthService {

    JwtDto login(LoginDto loginDto);

    JwtDto register(RegisterDto registerDto);
}
