package com.artur.jobaggregator.project.service;

import com.artur.jobaggregator.project.dto.MatchRequest;
import com.artur.jobaggregator.project.dto.MatchResult;
import com.artur.jobaggregator.project.entity.JobEntity;
import com.artur.jobaggregator.project.gemini.GeminiResponse;
import com.artur.jobaggregator.project.repository.JobRepository;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@Service
public class MatchService {
    private final JobRepository jobRepository;
    private final RestClient client;

    @Value("${gemini.api-key}")
    private String apiKey;

    public MatchService(JobRepository jobRepository, RestClient client) {
        this.jobRepository = jobRepository;
        this.client = client;
    }
    public MatchResult match(MatchRequest matchRequest, Long jobId) {
        JobEntity job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found: " + jobId));

        String cleanDescription = Jsoup.parse(job.getDescription()).text();

        String prompt = """
        You are an expert technical recruiter and ATS (Applicant Tracking System).
        Analyze how well the candidate's resume matches the job vacancy.
    
        Evaluate the match based on these criteria:
        1. Technical skills match (required technologies, languages, frameworks)
        2. Experience level match (years, seniority)
        3. Domain/industry relevance
        4. Education and certifications (if relevant)
        5. Soft skills and responsibilities alignment
    
        RESUME:
        %s
    
        JOB VACANCY:
        Title: %s
        Description: %s
        """
                .formatted(matchRequest.getResume(), job.getTitle(), cleanDescription);

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                ),
                "generationConfig", Map.of(
                        "responseMimeType", "application/json",
                        "responseSchema", Map.of(
                                "type", "object",
                                "properties", Map.of(
                                        "matchPercentage", Map.of("type", "integer"),
                                        "matchedSkills", Map.of("type", "array", "items", Map.of("type", "string")),
                                        "missingSkills", Map.of("type", "array", "items", Map.of("type", "string")),
                                        "experienceMatch", Map.of("type", "string"),
                                        "summary", Map.of("type", "string"),
                                        "recommendation", Map.of("type", "string")
                                )
                        )
                )
        );

        GeminiResponse geminiResponse = client
                .post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent")
                .header("X-goog-api-key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .body(GeminiResponse.class);

        String jsonResult = geminiResponse
                .getCandidates()
                .getFirst()
                .getContent()
                .getParts()
                .getFirst()
                .getText();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResult, MatchResult.class);

    }

}
