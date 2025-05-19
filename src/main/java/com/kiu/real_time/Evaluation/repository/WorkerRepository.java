package com.kiu.real_time.Evaluation.repository;

import com.kiu.real_time.person.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
