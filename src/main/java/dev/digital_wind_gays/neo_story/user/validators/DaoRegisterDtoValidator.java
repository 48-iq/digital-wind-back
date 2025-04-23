package dev.digital_wind_gays.neo_story.user.validators;

import dev.digital_wind_gays.neo_story.user.dto.RegisterDto;
import dev.digital_wind_gays.neo_story.user.repository.UserRepository;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DaoRegisterDtoValidator implements Validator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(@Nonnull Class<?> clazz) {
        return RegisterDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@Nonnull Object target, @Nonnull Errors errors) {
        RegisterDto registerDto = (RegisterDto) target;
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            errors.rejectValue("username", "Duplicate username");
        }
    }
}
