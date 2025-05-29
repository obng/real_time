package com.kiu.real_time.function.Ownerevaluation;

import com.kiu.real_time.person.owner.Owner;
import com.kiu.real_time.person.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerEvaluationController {

    @Autowired
    private OwnerEvaluationRepository ownerEvaluationRepository;


    @Autowired
    private OwnerRepository ownerRepository;

    @PostMapping("/evaluate/owner")
    public OwnerEvaluation evaluateOwner(@RequestBody OwnerEvaluationRequest req) {
        Long ownerId = 1L; // 항상 1번 사장으로 고정
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("해당 사장이 존재하지 않습니다."));

        OwnerEvaluation eval = new OwnerEvaluation(
                req.getPaymentPunctuality(),
                req.getWorkEnvironment(),
                req.getWorkingHours(),
                owner
        );

        return ownerEvaluationRepository.save(eval);
    }


    // 사장 평가 요약
    @GetMapping("/summary/{ownerId}")
    public OwnerScoreSummary getownerSummary(@PathVariable Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("해당 사장이 존재하지 않습니다."));

        List<OwnerEvaluation> evals = ownerEvaluationRepository.findByOwner(owner);

        int base = 50;

        // 각 평가 항목에 대한 점수 계산
        int payScore = base + adjustScore(evals.stream().mapToInt(OwnerEvaluation::getPaymentPunctuality).sum(), "paymentPunctuality");
        int envScore = base + adjustScore(evals.stream().mapToInt(OwnerEvaluation::getWorkEnvironment).sum(), "workEnvironment");
        int timeScore = base + adjustScore(evals.stream().mapToInt(OwnerEvaluation::getWorkingHours).sum(), "workingHours");

        return new OwnerScoreSummary(owner.getName(), payScore, envScore, timeScore);
    }

    // 점수 조정 함수 (좋은 점수는 천천히, 나쁜 점수는 빠르게)
    private int adjustScore(int totalDelta, String type) {
        double score = totalDelta;

        if (score > 0) {  // 좋은 점수일 때
            if (type.equals("paymentPunctuality")) {
                score = (int) (score * 0.1);  // 급여 재떄 주는 지
            } else if (type.equals("workEnvironment")) {
                score = (int) (score * 0.2);  // 업무 환경
            } else if (type.equals("workingHours")) {
                score = (int) (score * 0.2);  // 근무 시간 재떄 지키는 지
            }
        } else {  // 나쁜 점수일 때
            if (type.equals("paymentPunctuality")) {
                score = (int) (score * 0.5);
            } else if (type.equals("workEnvironment")) {
                score = (int) (score * 0.5);
            } else if (type.equals("workingHours")) {
                score = (int) (score * 0.75);
            }
        }

        return (int) score;
    }
}
