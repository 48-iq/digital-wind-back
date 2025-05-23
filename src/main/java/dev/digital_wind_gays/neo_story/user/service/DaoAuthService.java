package dev.digital_wind_gays.neo_story.user.service;

import dev.digital_wind_gays.neo_story.core.services.UuidService;
import dev.digital_wind_gays.neo_story.user.dto.JwtDto;
import dev.digital_wind_gays.neo_story.user.dto.LoginDto;
import dev.digital_wind_gays.neo_story.user.dto.RegisterDto;
import dev.digital_wind_gays.neo_story.user.entity.User;
import dev.digital_wind_gays.neo_story.user.exception.UserAlreadyExistsException;
import dev.digital_wind_gays.neo_story.user.repository.UserRepository;
import dev.digital_wind_gays.neo_story.user.security.EntityUserDetails;
import dev.digital_wind_gays.neo_story.user.validator.DaoRegisterDtoValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class DaoAuthService implements AuthService {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DaoRegisterDtoValidator registerDtoValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UuidService uuidService;

    @Override
    public JwtDto login(@Valid LoginDto loginDto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        );

        authentication = authenticationManager.authenticate(authentication);
        EntityUserDetails userDetails = (EntityUserDetails) authentication.getPrincipal();
        return new JwtDto(jwtService.generate(userDetails.getUser()));
    }

    @Override
    public JwtDto register(@Valid RegisterDto registerDto) {
        BindingResult errors = new BeanPropertyBindingResult(registerDto, "registerDto");
        registerDtoValidator.validate(registerDto, errors);

        if (errors.hasErrors()) {
            throw new UserAlreadyExistsException("User with username " + registerDto.getUsername() + " already exists");
        }

        User user = User.builder()
                .id(uuidService.generate())
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();

        user = userRepository.save(user);
        return new JwtDto(jwtService.generate(user));
    }
}
