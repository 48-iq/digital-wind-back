package dev.digital_wind_gays.neo_story.user.service;

import dev.digital_wind_gays.neo_story.user.dto.UserDto;

public interface UserService {
    UserDto getUserById(String id);
}
