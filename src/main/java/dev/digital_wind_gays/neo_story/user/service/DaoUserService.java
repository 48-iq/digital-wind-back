package dev.digital_wind_gays.neo_story.user.service;

import dev.digital_wind_gays.neo_story.user.dto.UserDto;
import dev.digital_wind_gays.neo_story.user.entity.User;
import dev.digital_wind_gays.neo_story.user.exception.UserNotFoundException;
import dev.digital_wind_gays.neo_story.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
