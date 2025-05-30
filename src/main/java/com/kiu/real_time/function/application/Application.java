package com.kiu.real_time.function.application;

import com.kiu.real_time.person.worker.Worker;
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
    private String status; // "대기", "승인", "거절" 등

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id", nullable = false)
    private JobPosting jobPosting;

    public static final String STATUS_PENDING = "대기";
    public static final String STATUS_CONFIRMED = "승인";
    public static final String STATUS_REJECTED = "거절";
    public static final String STATUS_COMPLETED = "마감";

    public Application(LocalDateTime appliedAt, String status, Worker worker, JobPosting jobPosting) {
        this.appliedAt = appliedAt;
        this.status = status;
        this.worker = worker;
        this.jobPosting = jobPosting;
    }

    public Application() {

    }
}
