package com.artur.jobaggregator.project;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/api/jobs/fetch")
    public void fetchJobs() {
        jobService.fetchAndSaveJobs();
    }

    @GetMapping("/api/jobs/{id}")
    public JobEntity getOne(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @GetMapping("api/jobs/get")
    public List<JobEntity> getAll() {
        return jobService.getAllJobs();
    }

    @GetMapping("api/jobs/search")
    public List<JobEntity> search(@RequestParam(required = false) String keyword,
                                  @RequestParam(required = false) String location,
                                  @RequestParam(required = false) Boolean remote,
                                  @RequestParam(defaultValue = "title") String sortBy,
                                  @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

        return jobService.searchJobs(keyword, location, remote, sort);
    }

}
