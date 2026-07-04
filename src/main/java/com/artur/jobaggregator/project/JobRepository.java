package com.artur.jobaggregator.project;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository <JobEntity, Long> {
    @Query(
            "SELECT j FROM JobEntity j " +
            "WHERE (:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT ('%', :keyword, '%')) OR " +
            "LOWER(j.description) LIKE LOWER(CONCAT ('%', :keyword, '%'))) " +
            "AND (:remote is NULL OR j.remote = :remote) " +
            "AND (:location is NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%')))"
    )
    List<JobEntity> search (
            @Param("keyword") String keyword,
            @Param("location") String location,
            @Param("remote") Boolean remote,
            Sort sort
    );
}
