package com.kiu.real_time.function.application;

import com.kiu.real_time.person.worker.Worker;
import com.kiu.real_time.person.worker.WorkerRepository;
import com.kiu.real_time.job_postings.JobPosting;
import com.kiu.real_time.job_postings.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final WorkerRepository workerRepository;
    private final JobPostingRepository jobPostingRepository;

    @Transactional
    public Application apply(Long workerId, Long jobPostingId) {
        if (applicationRepository.existsByWorkerIdAndJobPostingId(workerId, jobPostingId)) {
            throw new IllegalArgumentException("이미 지원한 공고입니다.");
        }
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 알바생입니다."));
        JobPosting jobPosting = jobPostingRepository.findById(Math.toIntExact(jobPostingId))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공고글입니다."));

        Application application = new Application(
                LocalDateTime.now(),
                "대기",
                worker,
                jobPosting
        );
        return applicationRepository.save(application);
    }


}
