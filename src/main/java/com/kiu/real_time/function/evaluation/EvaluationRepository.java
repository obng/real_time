package com.kiu.real_time.function.evaluation;

import com.kiu.real_time.person.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByWorker(Worker worker);
}

