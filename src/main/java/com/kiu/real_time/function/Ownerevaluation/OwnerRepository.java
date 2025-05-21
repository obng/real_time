package com.kiu.real_time.function.Ownerevaluation;

import com.kiu.real_time.function.evaluation.Evaluation;
import com.kiu.real_time.person.owner.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Evaluation> findByWorker(Owner owner);
}
