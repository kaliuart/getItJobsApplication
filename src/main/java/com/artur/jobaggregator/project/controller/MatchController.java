package com.artur.jobaggregator.project.controller;

import com.artur.jobaggregator.project.dto.MatchRequest;
import com.artur.jobaggregator.project.dto.MatchResult;
import com.artur.jobaggregator.project.service.MatchService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/api/match/{jobId}")
    public MatchResult matchResume(@RequestBody MatchRequest matchRequest, @PathVariable Long jobId) {
        return matchService.match(matchRequest, jobId);

    }


}
