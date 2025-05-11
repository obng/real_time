package com.kiu.real_time.Evaluation.repository;

import com.kiu.real_time.Evaluation.model.Evaluation;
import com.kiu.real_time.Evaluation.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByWorker(Worker worker);
}

