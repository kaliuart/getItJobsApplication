package com.artur.jobaggregator.source;

import com.artur.jobaggregator.entity.JobEntity;

import java.util.List;

public interface JobSource {
    List<JobEntity> fetchJobs();
}
