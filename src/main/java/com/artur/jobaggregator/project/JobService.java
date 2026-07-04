package com.artur.jobaggregator.project;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final RestClient client;
    private final Logger logger = LoggerFactory.getLogger(JobService.class);

    public JobService(JobRepository jobRepository, RestClient client) {
        this.jobRepository = jobRepository;
        this.client = client;
    }

    public void fetchAndSaveJobs() {
        JobResponse response = client.get()
                .uri("https://www.arbeitnow.com/api/job-board-api")
                .retrieve()
                .body(JobResponse.class);
        if (response == null | response.getData() == null) {
            logger.info("Data are empty");
            return;
        }
        jobRepository.saveAll(response.getData());

    }

    public List<JobEntity> getAllJobs() {
        return jobRepository.findAll();
    }

    public JobEntity getJobById(Long id) {
        return jobRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Job was not found by that id" + id
                        )
                );
    }


}
