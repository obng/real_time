package com.kiu.real_time.function.application;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByWorkerIdAndJobPostingId(Long workerId, Long jobPostingId);
}
