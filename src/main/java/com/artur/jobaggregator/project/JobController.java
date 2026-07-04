package com.artur.jobaggregator.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/api/jobs/fetch")
    public void fetchJobs() {
        jobService.fetchAndSaveJobs();
    }

    @GetMapping("/api/jobs/{id}")
    public void getOne(@PathVariable Long id) {
        jobService.getJobById(id);
    }

    @GetMapping("api/jobs/get")
    public void getAll() {
        jobService.getAllJobs();
    }
}
