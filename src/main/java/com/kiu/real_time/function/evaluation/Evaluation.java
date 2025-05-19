// 평가 점수

package com.kiu.real_time.function.evaluation;

import com.kiu.real_time.person.Worker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Evaluation {

    // Getter & Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private int sincerityDelta;
    @Setter
    private int lateDelta;
    @Setter
    private int absentDelta;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    // 기본 생성자 (JPA용)
    public Evaluation() {
    }

    public Evaluation(int sincerityDelta, int lateDelta, int absentDelta, Worker worker) {
        this.sincerityDelta = sincerityDelta;
        this.lateDelta = lateDelta;
        this.absentDelta = absentDelta;
        this.worker = worker;
    }

}