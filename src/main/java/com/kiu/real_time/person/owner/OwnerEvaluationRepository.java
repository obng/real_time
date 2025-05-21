package com.kiu.real_time.person.owner;

import com.kiu.real_time.function.Ownerevaluation.OwnerEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OwnerEvaluationRepository extends JpaRepository<OwnerEvaluation, Long> {
    List<OwnerEvaluation> findByBoss(Owner owner);
}
