package dev.digital_wind_gays.neo_story.ending.service;

import dev.digital_wind_gays.neo_story.ending.dto.EndingDto;

import java.util.List;

public interface EndingService {
    EndingDto openEnding(String id, String userId);

    List<EndingDto> getOpenedEndings(String userId);
}
