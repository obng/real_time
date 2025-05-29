package com.kiu.real_time.person.worker;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// WorkerDto.java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private BigDecimal rating;
    private List<AppliedJobDto> appliedJobs;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AppliedJobDto {
        private Long applicationId;
        private String status; // "대기", "승인", "거절" 등
        private String jobDescription;
        private String workLocation;
        private LocalDateTime appliedAt;
        private String ownerName;
        private String ownerPhone;
    }

    public static WorkerDto from(Worker worker, List<AppliedJobDto> appliedJobs) {
        return new WorkerDto(
                worker.getId(),
                worker.getName(),
                worker.getPhoneNumber(),
                worker.getRating(),
                appliedJobs
        );
    }
}
