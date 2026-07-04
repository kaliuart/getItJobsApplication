package com.artur.jobaggregator.project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository <JobEntity, Long> {
}
