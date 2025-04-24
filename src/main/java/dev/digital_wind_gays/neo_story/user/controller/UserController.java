package dev.digital_wind_gays.neo_story.user.controller;

import dev.digital_wind_gays.neo_story.user.dto.UserDto;
import dev.digital_wind_gays.neo_story.user.exception.UserNotFoundException;
import dev.digital_wind_gays.neo_story.user.security.EntityUserDetails;
import dev.digital_wind_gays.neo_story.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<UserDto> getUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            EntityUserDetails userDetails = (EntityUserDetails) authentication.getPrincipal();
            String userId = userDetails.getUser().getId();
            return ResponseEntity.ok(userService.getUserById(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
