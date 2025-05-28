package com.kiu.real_time.function.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
    private Long id;
    private String status;
    private LocalDateTime appliedAt;
    private Long workerId;
    private Long jobPostingId;
}
