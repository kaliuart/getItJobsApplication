package com.artur.jobaggregator.project.source;

import com.artur.jobaggregator.project.entity.JobEntity;

import java.util.List;

public interface JobSource {
    List<JobEntity> fetchJobs();
}
