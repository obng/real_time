package com.kiu.real_time.person.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;

    // ID로 Worker(알바생) 조회
    public Optional<Worker> findWorkerById(Long id) {
        return workerRepository.findById(id);
    }
}
