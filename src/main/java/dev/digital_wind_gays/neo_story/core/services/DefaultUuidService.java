package dev.digital_wind_gays.neo_story.core.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultUuidService implements UuidService{
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
