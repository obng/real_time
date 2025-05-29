package com.kiu.real_time.person.worker;

import com.kiu.real_time.function.application.Application;
import com.kiu.real_time.function.application.ApplicationRepository;
import com.kiu.real_time.job_postings.JobPosting;
import com.kiu.real_time.person.owner.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/worker")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;
    private final ApplicationRepository applicationRepository;

    @GetMapping("/{id}")
    public ResponseEntity<WorkerDto> getWorkerProfile(@PathVariable("id") Long id) {
        Worker worker = workerService.findWorkerById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 알바생을 찾을 수 없습니다."));

        // 지원한 근무(신청) 목록 조회
        List<Application> applications = applicationRepository.findByWorker(worker);

        List<WorkerDto.AppliedJobDto> appliedJobs = applications.stream()
                .map(app -> {
                    JobPosting posting = app.getJobPosting();
                    Owner owner = posting.getOwner();
                    return new WorkerDto.AppliedJobDto(
                            app.getId(),
                            app.getStatus(),
                            posting.getJobDescription(),
                            posting.getWorkLocation(),
                            app.getAppliedAt(),
                            owner.getName(),
                            owner.getPhoneNumber()
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(WorkerDto.from(worker, appliedJobs));
    }
}
