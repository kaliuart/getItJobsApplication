package com.artur.jobaggregator.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ConcurrentModificationException;

@SpringBootApplication
public class ProjectGetJobsSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectGetJobsSpringApplication.class, args);

    }
   @Bean
    CommandLineRunner run(JobService jobService) {
        return args -> jobService.fetchAndSaveJobs();
    }

}
