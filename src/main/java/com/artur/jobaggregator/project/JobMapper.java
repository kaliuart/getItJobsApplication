package com.artur.jobaggregator.project;

import com.artur.jobaggregator.project.dto.JobDto;
import com.artur.jobaggregator.project.entity.JobEntity;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    public JobDto mapToJobDto(JobEntity jobEntity) {
        JobDto job = new JobDto();

        job.setTitle(jobEntity.getTitle());
        job.setDescription(jobEntity.getDescription());
        job.setRemote(jobEntity.isRemote());
        job.setUrl(jobEntity.getUrl());
        job.setCompanyName(jobEntity.getCompanyName());
        job.setTags(jobEntity.getTags());
        job.setLocation(jobEntity.getLocation());

        return job;
    }
}
