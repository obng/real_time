package com.kiu.real_time.job_postings;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class JobPostingDto {
    private Long id;
    private String workingHours;
    private Integer numberOfWorkers;
    private String workLocation;
    private String jobDescription;
    private BigDecimal dailyWage;
    private Timestamp createdAt;
    private Long ownerId;
    private String ownerName;
}