package com.kiu.real_time.function.application;

import com.kiu.real_time.function.notification.OwnerNotificationController;
import com.kiu.real_time.function.notification.WorkerNotificationController;
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
    private final OwnerNotificationController ownerNotificationController;
    private final WorkerNotificationController workerNotificationController;

    /**
     * 알바생이 지원하면 사장님에게 알림 전송
     */
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
        Application saved = applicationRepository.save(application);

        // 사장님에게 알림 전송
        Long ownerId = jobPosting.getOwner().getId();
        String message = String.format("'%s'님이 [%s] 공고에 지원했습니다.", worker.getName(), jobPosting.getJobDescription());
        ownerNotificationController.sendNotification(ownerId, message, "info");

        return saved;
    }

    /**
     * 사장님이 지원을 수락하면 알바생에게 알림 전송
     */
    @Transactional
    public void acceptApplication(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("지원 내역을 찾을 수 없습니다."));

        // 지원 상태 변경
        application.setStatus("승인");
        applicationRepository.save(application);

        // 알바생에게 알림 전송
        Long workerId = application.getWorker().getId();
        String message = String.format("'%s' 공고에 지원이 수락되었습니다!", application.getJobPosting().getJobDescription());
        workerNotificationController.sendNotification(workerId, message, "success");
    }

    public void complete(Long applicationId) {
        // 기존 complete 로직
    }


}
