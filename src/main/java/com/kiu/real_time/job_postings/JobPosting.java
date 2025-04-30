package com.kiu.real_time.job_postings;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "job_postings")
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    private Integer workingHours; // 근무 시간

    private Integer numberOfWorkers; // 인력 수

    private String gender; // 성별

    private String workLocation; // 근무지

    @Column(columnDefinition = "TEXT")
    private String jobDescription; // 업무 내용

    private BigDecimal dailyWage; // 일당

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt; // 작성 시간


}
