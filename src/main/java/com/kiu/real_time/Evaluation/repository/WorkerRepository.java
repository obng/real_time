package com.kiu.real_time.Evaluation.repository;

import com.kiu.real_time.Evaluation.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//import com.example.job.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}