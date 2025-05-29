package com.kiu.real_time.function.application;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByWorkerIdAndJobPostingId(Long workerId, Long jobPostingId);
}
