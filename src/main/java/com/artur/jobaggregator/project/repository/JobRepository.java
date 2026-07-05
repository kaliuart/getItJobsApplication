package com.artur.jobaggregator.project.repository;

import java.util.Optional;

import com.artur.jobaggregator.project.entity.JobEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository <JobEntity, Long> {
    @Query(
            "SELECT j FROM JobEntity j " +
            "WHERE (:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT ('%', CAST(:keyword AS String), '%')) OR " +
            "LOWER(j.description) LIKE LOWER(CONCAT ('%', CAST(:keyword AS String), '%'))) " +
            "AND (:remote is NULL OR j.remote = :remote) " +
            "AND (:location is NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', CAST(:location AS String), '%')))"
    )
    List<JobEntity> search (
            @Param("keyword") String keyword,
            @Param("location") String location,
            @Param("remote") Boolean remote,
            Sort sort
    );

    Optional<JobEntity> findBySlug(String slug);
}
