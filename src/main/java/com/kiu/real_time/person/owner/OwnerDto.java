package com.kiu.real_time.person.owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private BigDecimal rating;
    private List<JobPostingWithApplicants> jobPostings;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JobPostingWithApplicants {
        private Long jobPostingId;
        private String jobDescription;
        private String workLocation;
        private List<ApplicantInfo> applicants;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplicantInfo {
        private Long id;
        private String name;
        private String phoneNumber;
        private String status; // "대기", "승인", "거절"
        private Long applicationId; // 수락/거절 처리용
    }

    public static OwnerDto from(Owner owner, List<JobPostingWithApplicants> jobPostings) {
        return new OwnerDto(
                owner.getId(),
                owner.getName(),
                owner.getPhoneNumber(),
                owner.getRating(),
                jobPostings
        );
    }
}
