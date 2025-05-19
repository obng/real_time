// 알바생 지원 내역

package com.kiu.real_time.function.application;

import com.kiu.real_time.person.Worker;
import com.kiu.real_time.job_postings.JobPosting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private LocalDateTime appliedAt;

    @Setter
    private String status; // 예: "대기", "승인", "거절" 등

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id", nullable = false)
    private JobPosting jobPosting; // 지원한 공고글

    public Application() {}

    public Application(LocalDateTime appliedAt, String status, Worker worker, JobPosting jobPosting) {
        this.appliedAt = appliedAt;
        this.status = status;
        this.worker = worker;
        this.jobPosting = jobPosting;
    }
}
