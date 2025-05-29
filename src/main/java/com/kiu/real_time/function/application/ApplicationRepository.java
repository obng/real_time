package com.kiu.real_time.function.application;

import com.kiu.real_time.job_postings.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByWorkerIdAndJobPostingId(Long workerId, Long jobPostingId);

    List<Application> findByJobPosting(JobPosting posting);
}
