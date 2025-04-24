package dev.digital_wind_gays.neo_story.user.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.digital_wind_gays.neo_story.user.entity.User;

public interface JwtService {
    DecodedJWT verify(String token);
    String generate(User user);
}
