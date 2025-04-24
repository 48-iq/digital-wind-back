package dev.digital_wind_gays.neo_story.ending.service;

import dev.digital_wind_gays.neo_story.ending.dto.EndingDto;
import dev.digital_wind_gays.neo_story.ending.entity.Ending;
import dev.digital_wind_gays.neo_story.ending.repository.EndingRepository;
import dev.digital_wind_gays.neo_story.user.entity.User;
import dev.digital_wind_gays.neo_story.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaoEndingService implements EndingService {
    @Autowired
    private EndingRepository endingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public EndingDto openEnding(String id, String userId) {
        User user = userRepository.getReferenceById(userId);
        Ending ending = Ending.builder()
                .id(id)
                .user(user)
                .build();
        ending = endingRepository.save(ending);
        return EndingDto.builder()
                .id(ending.getId())
                .build();
    }

    @Override
    public List<EndingDto> getOpenedEndings(String userId) {
        return endingRepository.findByUserId(userId)
                .stream().map(ending -> EndingDto.builder()
                        .id(ending.getId())
                        .build())
                .toList();
    }
}
