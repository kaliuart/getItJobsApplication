package com.artur.jobaggregator.project.service;

import com.artur.jobaggregator.project.dto.matching.MatchRequestDto;
import com.artur.jobaggregator.project.entity.JobEntity;
import com.artur.jobaggregator.project.exception.notfound.JobNotFoundException;
import com.artur.jobaggregator.project.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private RestClient client;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private MatchService matchService;


    @Test
    void getRequestBody_jobNotFound_throwsNotFound () {
        Long jobId = 15L;

        MatchRequestDto matchRequestDto = new MatchRequestDto();
        matchRequestDto.setResume("resume");

        when(jobRepository.findById(jobId)).thenReturn(Optional.empty());

        JobNotFoundException exception = assertThrows(
                JobNotFoundException.class,
                () -> matchService.getRequestBody(matchRequestDto, jobId)
        );

        assertEquals("Job not found with ID " + jobId, exception.getMessage());

    }
}