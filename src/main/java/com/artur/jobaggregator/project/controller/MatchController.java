package com.artur.jobaggregator.project.controller;

import com.artur.jobaggregator.project.dto.matching.MatchRequestDto;
import com.artur.jobaggregator.project.dto.matching.MatchResultDto;
import com.artur.jobaggregator.project.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/api/match/{jobId}")
    public MatchResultDto matchResume(@Valid @RequestBody MatchRequestDto matchRequest, @PathVariable Long jobId) {
        return matchService.match(matchRequest, jobId);

    }


}
