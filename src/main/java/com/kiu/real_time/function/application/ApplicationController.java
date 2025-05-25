package com.kiu.real_time.function.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<?> apply(
            @RequestParam Long workerId,
            @RequestParam Long jobPostingId
    ) {
        try {
            Application application = applicationService.apply(workerId, jobPostingId);
            return ResponseEntity.ok(application); // 지원 성공
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 지원 실패(예: 중복 지원 등)
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류가 발생했습니다."); // 기타 서버 오류
        }
    }
}
