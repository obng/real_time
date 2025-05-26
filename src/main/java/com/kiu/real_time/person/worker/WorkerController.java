package com.kiu.real_time.person.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    // 전체 Worker 목록 조회
    @GetMapping
    public ResponseEntity<List<WorkerDto>> getWorkers() {
        List<WorkerDto> workers = workerService.findAllWorkers().stream()
                .map(WorkerDto::from)
                .toList();
        return ResponseEntity.ok(workers);
    }

    // 단일 Worker 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<WorkerDto> getWorkerById(@PathVariable("id") Long id) {
        return workerService.findWorkerById(id)
                .map(worker -> ResponseEntity.ok(WorkerDto.from(worker)))
                .orElseThrow(() -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "해당 Worker를 찾을 수 없습니다."));
    }
}
