package dev.digital_wind_gays.neo_story.ending.controller;

import dev.digital_wind_gays.neo_story.ending.dto.EndingDto;
import dev.digital_wind_gays.neo_story.ending.service.EndingService;
import dev.digital_wind_gays.neo_story.user.security.EntityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ending")
public class EndingController {

    @Autowired
    private EndingService endingService;

    @GetMapping("/opened")
    public ResponseEntity<List<EndingDto>> getOpenedEndings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EntityUserDetails userDetails = (EntityUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(endingService.getOpenedEndings(userDetails.getUser().getId()));
    }

    @PostMapping("/open")
    public ResponseEntity<EndingDto> openEnding(@RequestParam String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EntityUserDetails userDetails = (EntityUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(endingService.openEnding(id, userDetails.getUser().getId()));
    }
}
