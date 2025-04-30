package com.kiu.real_time.Evaluation.controller;

//평가 등록 + 총점 조회

import com.kiu.real_time.Evaluation.dto.EvaluationRequest;  // 패키지 경로 수정
import com.kiu.real_time.Evaluation.dto.ScoreSummary;
import com.kiu.real_time.Evaluation.model.Evaluation;
import com.kiu.real_time.Evaluation.model.Worker;
import com.kiu.real_time.Evaluation.repository.EvaluationRepository;
import com.kiu.real_time.Evaluation.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EvaluationController {

    @Autowired
    private EvaluationRepository evaluationRepository; // 수정: evaluationRepo -> evaluationRepository

    @Autowired
    private WorkerRepository workerRepository; // 수정: workerRepo -> workerRepository

    // 평가 등록
    @PostMapping("/evaluate/{workerId}")
    public Evaluation evaluate(@PathVariable Long workerId, @RequestBody EvaluationRequest req) {
        // workerId로 Worker를 찾아오기
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("구직자가 존재하지 않습니다."));

        // Evaluation 객체 생성 및 저장
        Evaluation eval = new Evaluation(
                req.getSincerityDelta(),
                req.getLateDelta(),
                req.getAbsentDelta(),
                worker  // worker 설정
        );
        // 저장된 Evaluation 반환
        return evaluationRepository.save(eval);
    }

    // 점수 요약 조회
    @GetMapping("/summary/{workerId}")
    public ScoreSummary getSummary(@PathVariable Long workerId) {
        // workerId로 Worker를 찾아오기
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("구직자가 존재하지 않습니다."));

        // 해당 Worker의 Evaluation 목록 가져오기
        List<Evaluation> evals = evaluationRepository.findByWorkerId(workerId);

        int base = 50;

        // 점수 계산
        int sincerity = base + adjustScore(evals.stream().mapToInt(Evaluation::getSincerityDelta).sum(), "sincerity");
        int late = base + adjustScore(evals.stream().mapToInt(Evaluation::getLateDelta).sum(), "late");
        int absent = base + adjustScore(evals.stream().mapToInt(Evaluation::getAbsentDelta).sum(), "absent");

        // ScoreSummary 객체 생성 및 반환
        return new ScoreSummary(worker.getName(), sincerity, late, absent);
    }

    // 점수 조정 함수 (좋은 점수는 천천히, 나쁜 점수는 빠르게)
    private int adjustScore(int totalDelta, String type) {
        int score = totalDelta;

        if (score > 0) { // 좋은 점수일 때
            if (type.equals("sincerity")) {
                score *= 0.1;  // 성실은 천천히 증가
            } else if (type.equals("late") || type.equals("absent")) {
                score *= 0.2;  // 지각, 도망은 적당히 증가
            }
        } else {  // 나쁜 점수일 때
            if (type.equals("sincerity")) {
                score *= 0.5;  // 성실은 빠르게 감소
            } else if (type.equals("late") || type.equals("absent")) {
                score *= 0.75;  // 지각, 도망은 빠르게 감소
            }
        }

        return score;
    }
}