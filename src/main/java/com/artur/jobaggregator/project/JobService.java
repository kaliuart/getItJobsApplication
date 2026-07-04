package com.artur.jobaggregator.project;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
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
        if (response == null || response.getData() == null) {
            logger.info("Data are empty");
            return;
        }

        for (JobEntity job: response.getData()) {
            Optional<JobEntity> existing = jobRepository.findBySlug(job.getSlug());

            if (existing.isPresent()) {

                JobEntity jobEntity = existing.get();
                if (!jobEntity.equals(job)) {

                    jobEntity.setTitle(job.getTitle());
                    jobEntity.setDescription(job.getDescription());
                    jobEntity.setRemote(job.isRemote());
                    jobEntity.setCompanyName(job.getCompanyName());
                    jobEntity.setLocation(job.getLocation());
                    jobEntity.setUrl(job.getUrl());
                    jobEntity.setTags(job.getTags());

                    jobRepository.save(jobEntity);
                }
            }
            else {
                jobRepository.save(job);
            }
        }
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

    public List<JobEntity> searchJobs(String keyword, String location, Boolean remote, Sort sort) {
        return jobRepository.search(keyword, location, remote, sort);
    }

}
