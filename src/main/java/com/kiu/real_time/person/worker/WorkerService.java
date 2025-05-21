package com.kiu.real_time.person.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;

    // 전체 Worker(알바생) 조회
    public List<Worker> findAllWorkers() {
        return workerRepository.findAll();
    }

    // ID로 Worker(알바생) 조회
    public Optional<Worker> findWorkerById(Long id) {
        return workerRepository.findById(id);
    }
}
