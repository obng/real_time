package com.kiu.real_time.job_postings;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-postings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class JobPostingController {

    private final JobPostingService jobPostingService;
    private final JobPostingRepository jobPostingRepository;

    @GetMapping
    public ResponseEntity<List<JobPostingDto>> getRecentJobPostings() {
        List<JobPosting> postings = jobPostingRepository.findAllByOrderByCreatedAtDesc();
        List<JobPostingDto> dtos = postings.stream()
                .map(jobPostingService::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostingDto> getJobPostingById(@PathVariable Long id) {
        JobPosting posting = jobPostingService.getJobPostingById(Math.toIntExact(id));
        return ResponseEntity.ok(jobPostingService.toDto(posting));
    }

    @PostMapping
    public ResponseEntity<JobPostingDto> createJobPosting(@RequestBody JobPostingDto jobPostingDto) {
        JobPosting posting = jobPostingService.createJobPostingFromDto(jobPostingDto);
        return new ResponseEntity<>(jobPostingService.toDto(posting), HttpStatus.CREATED);
    }

}
