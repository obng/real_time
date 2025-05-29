package com.kiu.real_time.person.owner;

import com.kiu.real_time.function.application.Application;
import com.kiu.real_time.function.application.ApplicationRepository;
import com.kiu.real_time.job_postings.JobPosting;
import com.kiu.real_time.job_postings.JobPostingRepository;
import com.kiu.real_time.person.worker.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final JobPostingRepository jobPostingRepository;
    private final ApplicationRepository applicationRepository;

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getOwners() {
        List<OwnerDto> owners = ownerService.findAllOwners().stream()
                .map(owner -> OwnerDto.from(owner, null))
                .collect(Collectors.toList());
        return ResponseEntity.ok(owners);
    }

    // 단일 Owner 상세 조회 + 내가 올린 공고별 지원자 목록
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable("id") Long id) {
        Owner owner = ownerService.findOwnerById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 Owner를 찾을 수 없습니다."));

        List<JobPosting> postings = jobPostingRepository.findByOwnerId(owner.getId());

        List<OwnerDto.JobPostingWithApplicants> jobPostingsWithApplicants = postings.stream()
                .map(posting -> {
                    List<Application> applications = applicationRepository.findByJobPosting(posting);
                    List<OwnerDto.ApplicantInfo> applicants = applications.stream()
                            .map(app -> {
                                Worker worker = app.getWorker();
                                return new OwnerDto.ApplicantInfo(
                                        worker.getId(),
                                        worker.getName(),
                                        worker.getPhoneNumber(),
                                        app.getStatus(),
                                        app.getId()
                                );
                            })
                            .collect(Collectors.toList());
                    return new OwnerDto.JobPostingWithApplicants(
                            posting.getId(),
                            posting.getJobDescription(),
                            posting.getWorkLocation(),
                            applicants
                    );
                })
                .collect(Collectors.toList());

        OwnerDto dto = OwnerDto.from(owner, jobPostingsWithApplicants);

        return ResponseEntity.ok(dto);
    }

    // 지원 수락(승인) API
    @PostMapping("/applications/{applicationId}/accept")
    public ResponseEntity<?> acceptApplication(@PathVariable("applicationId") Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "지원 내역을 찾을 수 없습니다."));
        if (!Application.STATUS_PENDING.equals(application.getStatus())) {
            return ResponseEntity.badRequest().body("이미 처리된 지원입니다.");
        }
        application.setStatus(Application.STATUS_CONFIRMED);
        applicationRepository.save(application);
        return ResponseEntity.ok().body("수락 완료");
    }
}
