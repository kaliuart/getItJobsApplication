package com.artur.jobaggregator.project;

import com.artur.jobaggregator.project.entity.JobEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobResponse {
    private List<JobEntity> data;
}
