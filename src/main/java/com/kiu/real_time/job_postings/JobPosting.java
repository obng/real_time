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
    private Long id;

    @Column(name = "working_hours")
    private String workingHours;

    @Column(name = "number_of_workers")
    private Integer numberOfWorkers;

    @Column(name = "work_location")
    private String workLocation;

    @Column(name = "job_description", columnDefinition = "TEXT")
    private String jobDescription;

    @Column(name = "daily_wage")
    private BigDecimal dailyWage;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @org.hibernate.annotations.CreationTimestamp
    private Timestamp createdAt;
}

