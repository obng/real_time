package com.kiu.real_time.person.owner;

import com.kiu.real_time.function.application.Application;
import com.kiu.real_time.function.application.ApplicationRepository;
import com.kiu.real_time.job_postings.JobPosting;
import com.kiu.real_time.job_postings.JobPostingRepository;
import com.kiu.real_time.person.worker.Worker;
import lombok.RequiredArgsConstructor;
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

    // 전체 Owner 목록 조회 (프로필 정보만)
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
                .orElseThrow(() -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "해당 Owner를 찾을 수 없습니다."));

        // 1. 사장님이 올린 모든 공고 조회
        List<JobPosting> postings = jobPostingRepository.findByOwnerId(owner.getId());

        // 2. 각 공고별 지원자 목록 생성
        List<OwnerDto.JobPostingWithApplicants> jobPostingsWithApplicants = postings.stream()
                .map(posting -> {
                    List<Application> applications = applicationRepository.findByJobPosting(posting);
                    List<OwnerDto.ApplicantInfo> applicants = applications.stream()
                            .map(app -> {
                                Worker worker = app.getWorker();
                                return new OwnerDto.ApplicantInfo(
                                        worker.getId(),
                                        worker.getName(),
                                        worker.getPhoneNumber()
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

        // 3. OwnerDto로 변환 (지원자 목록 포함)
        OwnerDto dto = OwnerDto.from(owner, jobPostingsWithApplicants);

        return ResponseEntity.ok(dto);
    }
}
