package com.kiu.real_time.function.Ownerevaluation;

import com.kiu.real_time.person.owner.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OwnerEvaluationRepository extends JpaRepository<OwnerEvaluation, Long> {
    List<OwnerEvaluation> findByOwner(Owner owner);
}
