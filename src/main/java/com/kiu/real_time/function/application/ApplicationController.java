package com.kiu.real_time.function.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationRepository applicationRepository;

    @PostMapping
    public ResponseEntity<?> apply(
            @RequestParam("workerId") Long workerId,
            @RequestParam("jobPostingId") Long jobPostingId
    ) {
        try {
            Application application = applicationService.apply(workerId, jobPostingId);
            ApplicationDto dto = new ApplicationDto(
                    application.getId(),
                    application.getStatus(),
                    application.getAppliedAt(),
                    application.getWorker().getId(),
                    application.getJobPosting().getId()
            );
            return ResponseEntity.ok(dto); // DTO로 반환
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "서버 오류가 발생했습니다."));
        }
    }

    @PostMapping("/{applicationId}/complete")
    public ResponseEntity<?> completeApplication(@PathVariable("applicationId") Long applicationId) {
        try {
            applicationService.complete(applicationId);
            return ResponseEntity.ok().body("마감 처리 완료");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }



}
