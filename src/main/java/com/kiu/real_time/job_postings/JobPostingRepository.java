package com.kiu.real_time.job_postings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {
    List<JobPosting> findAllByOrderByCreatedAtDesc();
    List<JobPosting> findByOwnerId(Long ownerId);
}
