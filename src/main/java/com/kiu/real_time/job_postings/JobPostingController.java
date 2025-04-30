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

    @GetMapping
    public ResponseEntity<List<JobPosting>> getAllJobPostings() {
        return ResponseEntity.ok(jobPostingService.getAllJobPostings());
    }

    // 단일 공고 조회
    @GetMapping("/{id}")
    public ResponseEntity<JobPosting> getJobPostingById(@PathVariable Integer id) {
        return ResponseEntity.ok(jobPostingService.getJobPostingById(id));
    }

    // 공고 생성
    @PostMapping
    public ResponseEntity<JobPosting> createJobPosting(@RequestBody JobPosting jobPosting) {
        return new ResponseEntity<>(
                jobPostingService.createJobPosting(jobPosting),
                HttpStatus.CREATED
        );
    }
}
