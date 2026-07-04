package com.artur.jobaggregator.project;

import lombok.Data;

import java.util.List;

@Data
public class JobResponse {
    private List<JobEntity> data;
}
