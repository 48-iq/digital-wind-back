package dev.digital_wind_gays.neo_story.user.controller;

import dev.digital_wind_gays.neo_story.user.dto.JwtDto;
import dev.digital_wind_gays.neo_story.user.dto.LoginDto;
import dev.digital_wind_gays.neo_story.user.dto.RegisterDto;
import dev.digital_wind_gays.neo_story.user.exception.UserAlreadyExistsException;
import dev.digital_wind_gays.neo_story.user.service.AuthService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto ) {
        try {
            return ResponseEntity.ok(authService.login(loginDto));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        try {
            return ResponseEntity.ok(authService.register(registerDto));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
