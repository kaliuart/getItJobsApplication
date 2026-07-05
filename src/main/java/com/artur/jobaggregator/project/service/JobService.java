package com.artur.jobaggregator.project.service;

import com.artur.jobaggregator.project.entity.JobEntity;
import com.artur.jobaggregator.project.JobMapper;
import com.artur.jobaggregator.project.repository.JobRepository;
import com.artur.jobaggregator.project.JobResponse;
import com.artur.jobaggregator.project.dto.JobDto;
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
    private final JobMapper jobMapper;

    private final Logger logger = LoggerFactory.getLogger(JobService.class);

    public JobService(JobRepository jobRepository, RestClient client, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.client = client;
        this.jobMapper = jobMapper;
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

    public List<JobDto> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::mapToJobDto)
                .toList();
    }

    public JobDto getJobById(Long id) {
        return jobMapper.mapToJobDto(jobRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Job was not found by that id" + id
                        )
                )
        );
    }

    public List<JobDto> searchJobs(String keyword, String location, Boolean remote, Sort sort) {
        return jobRepository.search(keyword, location, remote, sort)
                .stream()
                .map(jobMapper::mapToJobDto)
                .toList();
    }

}
